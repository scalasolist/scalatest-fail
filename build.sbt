// credentials

organization in ThisBuild := "scalasolist"

organizationHomepage in ThisBuild := Some(url( "https://github.com/scalasolist" ))

// basic settings

name := "scalatest-fail"

description := "project to illustrate scalatest issues"

version := "0.1-SNAPSHOT"

startYear := Some(2014)

licenses := Seq("the Apache License, ASL Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

fork := true

(fork in Test) := false

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.0" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.4" % "test"
