name := "s-mach.www"

scalaVersion := "2.11.5"

// Scala Compiler Options
scalacOptions in ThisBuild ++= Seq(
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "-deprecation", // warning and location for usages of deprecated APIs
  "-feature", // warning and location for usages of features that should be imported explicitly
  "-unchecked", // additional warnings where generated code depends on assumptions
  "-Xlint", // recommended additional warnings
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code"
)

version := "1.0.0-SNAPSHOT"

// Note: required to resolve scalaz-streams (transitive for Specs2)
resolvers += "Scalaz Bintray Repo"  at "http://dl.bintray.com/scalaz/releases"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "net.s_mach" %% "string" % "1.0.0",
  "net.s_mach" %% "concurrent" % "1.1.0",
  "com.typesafe" % "config" % "1.2.1",
  // Webjars allows including js in project like jars
  "org.webjars" %% "webjars-play" % "2.4.0-M2",
  "org.webjars" % "bootstrap" % "3.3.2-1"
)

// Turn on clean/minify for css
//LessKeys.cleancss in Assets := true

// Only compile one less file
includeFilter in (Assets, LessKeys.less) := "main.less"

// Ensure re-written relative urls in less start with ../
LessKeys.rootpath in Assets := "../"

// Fix font-face urls (for bootstrap glyphicons from "../font/glyphicons..." to "../lib/bootstrap/fonts/glyphicons...")
LessKeys.relativeUrls in Assets := true

