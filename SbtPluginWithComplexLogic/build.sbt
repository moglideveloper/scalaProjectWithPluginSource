name := "sbt-plugin"

organization := "io.complex"

version := "1.0"

sbtPlugin := true

// https://mvnrepository.com/artifact/com.github.pathikrit/better-files
libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.9.1"

lazy val complexSbtPlugin = ( project in file("") )

