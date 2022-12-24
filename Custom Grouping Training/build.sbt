
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Custom Grouping Training"
  )

val StormV = "2.4.0"

resolvers ++= Seq(
  "clojars-repository" at "https://clojars.org/repo",
  "twttr" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "org.apache.storm" % "storm-core" % StormV % "provided"
)

assembly/assemblyJarName := "custom-grouping-1.0.jar"

val additionalResolvers = Seq(
  "clojars-repository" at "https://clojars.org/repo"
)