lazy val root = (project in file(".")).
  settings(
    name := "future promise actor",
    version := "2021.0",
    scalaVersion := "3.0.0-RC3"
  )

libraryDependencies ++= Seq(
  ("com.typesafe.akka" % "akka-actor" % "2.6.14").cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" % "akka-actor-typed" % "2.6.14").cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" % "akka-stream" % "2.6.14").cross(CrossVersion.for3Use2_13),
)

