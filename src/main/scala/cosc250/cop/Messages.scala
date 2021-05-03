package cosc250.cop

import akka.actor.ActorRef

/*
 * First, because we're working with Actors, we need some messages to pass
 * Note: we can also just pass a bare Int! Normally we wouldn't -- it's nice to have a single trait
 * or abstract class all our messages belong to -- but for this exercise let's show it's possible
 */

enum Word:
  /** If the number is divisible by 3, but not by 5 */
  case Fizz

  /** If the number is divisible by 5, but not by 3 */
  case Buzz

  /** If the number is divisible by 3 and 5 */
  case FizzBuzz

type FizzBuzzMessage = Word | Int

/**
  * Some messages that the referee can send
  */
enum RefereeMessage:
  case YourTurn
  case Wrong(was: FizzBuzzMessage, shouldBe: FizzBuzzMessage)
  case Victory
