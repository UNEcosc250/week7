package cosc250.cop.typed

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import cosc250.cop.Word.{Buzz, Fizz, FizzBuzz}
import cosc250.cop._


def fizzBuzzBehaviour(name:String, i:Int):Behavior[PlayerHears] = Behaviors.receive {
  case (context, RefereeSays(RefereeMessage.YourTurn, referee)) =>
    referee ! PlayerSays(fizzBuzz(i + 1), context.self)
    fizzBuzzBehaviour(name, i)

  case (context, RefereeSays(_, _)) =>
    fizzBuzzBehaviour(name, i + 1)
}