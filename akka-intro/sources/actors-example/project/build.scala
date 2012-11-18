import sbt._
import Keys._

object MonitoringServicesBuild extends Build {
  import Dependencies._
  import Resolvers._

  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "com.github.migue",
    version      := "0.0.1",
    scalaVersion := "2.10.0-RC1",
    javacOptions ++= Seq("-target", "1.6", "-source", "1.6"),
    resolvers ++= Seq(typesafeReleasesRepository, typesafeSnapshotsRepository)
  )

  lazy val root = Project(
    id = "monitoring-project",
    base = file("."),
    settings = buildSettings ++ Seq(
      description := "Monitoring system"
    ),
    aggregate = Seq(monitoringSystem, clientMetrics, messages)
  )
  
  lazy val messages = Project(
    id="messages",
    base = file("messages"),
    settings = buildSettings
  )

  lazy val monitoringSystem = Project(
    id = "monitoring-system",
    base = file("monitoring-system"),
    settings = buildSettings ++ Seq(
     libraryDependencies ++= Seq(akkaActor, akkaKernel, akkaRemote),
     description := "Metrics processing system" 
    )
  ) dependsOn(messages)
  
  lazy val clientMetrics = Project(
    id = "client-metrics",
    base = file("client-metrics"),
    settings = buildSettings ++ Seq(
      libraryDependencies ++= Seq(akkaActor, akkaKernel, akkaRemote),
      description := "Client metrics responsible of handling the metrics in the clients" 
    )
  ) dependsOn(messages)
  

  object Dependencies {
    val akkaVersion = "2.1-SNAPSHOT"

    val akkaActor = "com.typesafe.akka" % "akka-actor" % akkaVersion
    
    val akkaKernel = "com.typesafe.akka" % "akka-kernel" % akkaVersion
  
    val akkaRemote = "com.typesafe.akka" % "akka-remote" % akkaVersion

    // val jUnit = "junit" % "junit" % "4.10" % "test"
  }

  object Resolvers {
    //val sonatypeNexusSnapshots = "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    //val sonatypeNexusStaging = "Sonatype Nexus Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    val typesafeReleasesRepository = "Typesafe Repo Releases" at "http://repo.typesafe.com/typesafe/releases/"
    val typesafeSnapshotsRepository = "Typesafe Repo Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
  }
}