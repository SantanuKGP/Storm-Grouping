
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "multiple-fields-grouping"
  )
resolvers ++= Seq(
  "clojars-repository" at "https://clojars.org/repo",
  "twttr" at "https://maven.twttr.com"
)

val logbackVersion = "1.2.10"
val slf4j_version = "1.7.5"
val log4j_api_version = "2.17.2"
val StormV = "2.4.0"

libraryDependencies ++= Seq(
  "org.apache.storm" % "storm-core" % StormV % "provided"
)

assembly/assemblyJarName := "storm-topology-2.jar"










val additionalResolvers = Seq(
  "clojars-repository" at "https://clojars.org/repo"
)