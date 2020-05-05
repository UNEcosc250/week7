lazy val root = (project in file(".")).
  settings(
    name := "future promise actor",
    version := "1.0",
    scalaVersion := "2.13.1"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.5",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.5",
  "com.typesafe.akka" %% "akka-stream" % "2.6.5",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.scalactic" %% "scalactic" % "3.1.1",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.1.2",
)

