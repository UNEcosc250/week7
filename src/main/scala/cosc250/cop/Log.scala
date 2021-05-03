package cosc250.cop.untyped

import akka.actor.Actor

/**
  * This is a little utility method I've made. It creates a PartialFunction that
  * writes out whatever it received.
  *
  * PartialFunctions can be composed together using "andThen". So if you want a method
  * that logs what it receives and then takes an action depending on what method it receives...
  *
  * log("MyActor") andThen { case ... }
  */
def log(name: String): PartialFunction[Any, Any] = {
  case m =>
    println(s"$name received $m")
    m
}


