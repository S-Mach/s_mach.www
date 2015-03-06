name := "s-mach.www"

scalaVersion := "2.11.5"

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

