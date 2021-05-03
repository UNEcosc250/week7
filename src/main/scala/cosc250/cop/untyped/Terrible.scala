package cosc250.cop.untyped

import akka.actor.{Actor, ActorRef}
import cosc250.cop._
import Word._

import scala.util.Random

/**
  * This Actor is shockingly awful at this game. It gets the number and increments it,
  * but
  */
class Terrible extends Actor {

  private var num:Int = 0

  def nextResponse() = {
    Seq(
      num + 1,
      Fizz,
      Buzz,
      FizzBuzz
    )(Random.nextInt(4))
  }

  def respond() = {
    val n = nextResponse()
    println("Terrible says " + n)
    sender() ! n
  }

  def receive = log("Terrible") andThen {
    case RefereeMessage.YourTurn => respond()
    case _ =>
      num += 1
  }

}
