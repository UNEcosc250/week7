package cosc250.cop.untyped

import akka.actor.Actor
import cosc250.cop._
import cosc250.cop.Word.{Buzz, Fizz, FizzBuzz}


/*
 * Now you need to define your FizzBuzz Actor...
 */
class FizzBuzzActor(name:String) extends Actor {

  private var number = 0

  def fizzBuzz(i:Int):FizzBuzzMessage =
    if i % 15 == 0 then FizzBuzz
    else if i % 5 == 0 then Buzz
    else if i % 3 == 0 then Fizz
    else i

  def reply() = {
    val r = fizzBuzz(number + 1)
    println(s"$name sent $r")
    sender() ! r
  }

  /*
   * In class, I hadn't shown the type of the receive method. It's a PartialFunction!
   * (Ah, the joy of being able to link back to earlier lectures...)
   *
   * Curiously, this means you can also change the def to a val and it will still work.
   * I'll leave why that is as an exercise for the reader...
   */
  def receive: PartialFunction[Any, Unit] = log(name) andThen {

    // Now decide how your actor is going to respond to the messages. Note, you might need to
    // Create member variables and functions...

    // We have to have at least one case statement to make this compile as a PartialFunction
    // with the types...
    case RefereeMessage.YourTurn => {
      reply()
    }
    case _  =>
      number += 1

  }
}