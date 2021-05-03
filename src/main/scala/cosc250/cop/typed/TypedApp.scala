package cosc250.cop.typed

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

@main def typedApp() = {

  def setUp = Behaviors.setup[RefereeHears] { context =>
    val algernon = context.spawn(fizzBuzzBehaviour("Algernon", 0), "Algernon")
    val bertie = context.spawn(fizzBuzzBehaviour("Bertie", 0), "Bertie")

    referee(RefereeState(0, 100, List(algernon, bertie)))
  }

  val system = ActorSystem[RefereeHears](setUp, "referee")
  println("Created the actor system")

  system ! Go

}
