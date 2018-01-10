import sbt.Keys.{resolvers, test, testOnly, testOptions, _}
import sbt.Resolver

inThisBuild(Seq(
  organization := "com.autoscout24",
  version := Option(System.getenv("GO_PIPELINE_LABEL")).getOrElse("1.0-SNAPSHOT"),
  scalaVersion := "2.12.4",
  parallelExecution := false,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings", "-Ypartial-unification"),
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),
  resolvers += Resolver.bintrayRepo("tatsu-kondor", "maven"),
  resolvers += "AWS repo" at "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/release",
  resolvers += "JBoss public" at "http://repository.jboss.org/nexus/content/groups/public/",
  resolvers += Resolver.sonatypeRepo("snapshots")
))




    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,

      // Akka
        "com.typesafe.akka" %% "akka-stream" % "2.5.7",
        "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.7" % Test,


      "com.typesafe.akka" %% "akka-stream-kafka" % "0.18",

      // logging
      "org.slf4j" % "slf4j-api" % "1.7.25",
      "ch.qos.logback" % "logback-core" % "1.2.3",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "net.logstash.logback" % "logstash-logback-encoder" % "4.11",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",

      // other stuff
      "com.typesafe" % "config" % "1.3.0",
      "org.apache.kafka" % "kafka-clients" % "0.11.0.0",
      "org.apache.commons" % "commons-collections4" % "4.1",

      // test
      "org.scalatest" %% "scalatest" % "3.0.4" % Test,
      "org.scalactic" %% "scalactic" % "3.0.4",
      "org.mockito" % "mockito-all" % "1.10.19" % Test
    )


