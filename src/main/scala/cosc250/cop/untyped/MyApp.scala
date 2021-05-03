package cosc250.cop.untyped

import java.util.concurrent.TimeoutException
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration.FiniteDuration
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext.Implicits.global
import cosc250.cop._

@main def app() = {

  // Create the actor system
  val system = ActorSystem("PingPongSystem")
  system.whenTerminated.foreach(_ => System.exit(0))
  println("Created the actor system")

  val referee = system.actorOf(Props[Referee], name = "Referee")

  // Create three of your players
  val algernon = system.actorOf(Props(classOf[FizzBuzzActor], "Algernon"), name = "Algernon")
  val bertie = system.actorOf(Props(classOf[FizzBuzzActor], "Bertie"), name = "Bertie")
  val cecily = system.actorOf(Props(classOf[FizzBuzzActor], "Cecily"), name = "Cecily")

  // Create a terrible player
  val hello = system.actorOf(Props[Terrible], name = "Terrible")

  // Give the list of players to the referee to start the game
  referee ! Game(List(algernon, bertie, cecily, hello), 5)

}

