package cosc250.cop.typed

import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import cosc250.cop.Word.{Buzz, Fizz, FizzBuzz}
import cosc250.cop._



/**
  * We keep the state of the actor in a case class.
  * That also means it's a good place to put some functions to describe what a next state could be
  */
case class RefereeState(from:Int, to:Int, players:List[ActorRef[PlayerHears]]) {

  /** The state we get if we move on to the next turn (nobody eliminated) */
  def moveToNextPlayer:RefereeState = this.copy(from = from + 1, players = players.tail :+ players.head)

  /** The state we get if we eliminate a player and move on to the next turn */
  def eliminateAndNext(p:ActorRef[PlayerHears]):RefereeState = {
    val remaining = players.filter(_ != p)
    this.copy(from = from + 1, players = remaining.tail :+ remaining.head)
  }

}

def fizzBuzz(i:Int):FizzBuzzMessage =
  if i % 15 == 0 then FizzBuzz
  else if i % 5 == 0 then Buzz
  else if i % 3 == 0 then Fizz
  else i

/**
  * This will produce a referee "behaviour".
  * Note that the state is passed as a parameter.
  *
  * We have two jobs in the body of the function:
  * 1. React to the message, sending out any messages we want, and
  * 2. Work out what our new state should be, so we can return referee(newstate).
  */
def referee(state:RefereeState): Behavior[RefereeHears] = Behaviors.receive {
  case (context, Go) =>
    if state.players.nonEmpty then {
      // Tell the next player it's their turn
      state.players.head ! RefereeSays(RefereeMessage.YourTurn, context.self)

      // Our new behaviour moves on to the next player
      referee(state.moveToNextPlayer)
    } else {
      println("No players, no game!")
      Behaviors.stopped
    }

  case (context, PlayerSays(m, player)) =>
    // Re-broadcast the message
    for p <- state.players do p ! RefereeSays(m, context.self)

    // Check if the answer was right
    if m == fizzBuzz(state.from) then
      println(s"$player said $m, correctly")

      // Challenge the next player
      state.players.head ! RefereeSays(RefereeMessage.YourTurn, context.self)

      // Next state would move on to the next number and the next player
      val newState = state.moveToNextPlayer

      if newState.from <= newState.to then
        referee(newState)
      else {
        println("Game over")
        Behaviors.stopped
      }
    else {
      // The player was wrong.
      println(s"$player was wrong! sent $m but should have been ${fizzBuzz(state.from)}")

      // Eliminate the player in the new state.
      val newState = state.eliminateAndNext(player)

      println(s"Remaining players: ${newState.players}")

      // If anyone's left, challenge the next player
      if newState.players.nonEmpty && newState.from <= newState.to then
        newState.players.head ! RefereeSays(RefereeMessage.YourTurn, context.self)
        referee(newState)
      else {
        println("Game over")
        Behaviors.stopped
      }

    }

}