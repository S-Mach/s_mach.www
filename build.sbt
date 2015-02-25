name := "s-mach"

scalaVersion := "2.11.5"

version := "1.0.0-SNAPSHOT"

resolvers += "Scalaz Bintray Repo"  at "http://dl.bintray.com/scalaz/releases"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0-M2",
  "org.webjars" % "bootstrap" % "3.3.2-1"
)
