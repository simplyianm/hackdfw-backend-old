name := "hackdfw-backend"
organization := "com.hackdfw"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.7"
parallelExecution in ThisBuild := false

enablePlugins(JavaServerAppPackaging)

lazy val versions = new {
  val finatra = "2.1.2"
  val logback = "1.0.13"
  val guice = "4.0"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case other => MergeStrategy.defaultMergeStrategy(other)
}

unmanagedResourceDirectories in Compile += {
  baseDirectory.value / "src/main/webapp"
}

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % versions.finatra,
  "com.twitter.finatra" %% "finatra-httpclient" % versions.finatra,
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra,
  "com.twitter.finatra" %% "finatra-slf4j" % versions.finatra,
  "com.twitter.inject" %% "inject-core" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback,

  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.github.tminglei" %% "slick-pg" % "0.10.2",
  "com.github.tminglei" %% "slick-pg_json4s" % "0.10.2",

  "org.json4s" %% "json4s-ast" % "3.2.10",
  "org.json4s" %% "json4s-core" % "3.2.10",
  "org.json4s" %% "json4s-jackson" % "3.2.10",

  "com.twitter.inject" %% "inject-server" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test",

  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.3" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test"
)


fork in run := true