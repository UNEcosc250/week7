package cosc250.cop.typed

import akka.actor.typed.ActorRef
import cosc250.cop._

// The big complication in Akka Typed is that messages need to include the sender.
// That's because if there was just a sender() method to get them, it wouldn't be possible for the compiler to know
// What type of message the sender can receive back in any replies.
// So, we end up needing to:
// * Have case classes for messages that can be sent (so we can include the ActorRef of the sender)
// * and have types for the messages that can be received

/** What a Player actor can say / send */
case class PlayerSays(message: FizzBuzzMessage, from: ActorRef[PlayerHears])

/** What a Referee actor can say / send */
case class RefereeSays(message: FizzBuzzMessage | RefereeMessage, from: ActorRef[RefereeHears])

/** A message to start the game */
case object Go

/** Players only ever hear from the referee */
type PlayerHears = RefereeSays

/** The referee hears from players, but can also receive a Go message to start the game */
type RefereeHears = PlayerSays | Go.type