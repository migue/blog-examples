import sbt._
import Keys._

object TwitterClientBuild extends Build {
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
    id = "twitter-client-project",
    base = file("."),
    settings = buildSettings ++ Seq(
      description := "Small Twitter client using futures"
    ),
    aggregate = Seq(twitterClient)
  )
  
  lazy val twitterClient = Project(
    id="twitter-client",
    base = file("twitter-client"),
    settings = buildSettings
  )

  object Dependencies {
    val akkaVersion = "2.1-SNAPSHOT"

    val akkaActor = "com.typesafe.akka" % "akka-actor" % akkaVersion
    
    val akkaKernel = "com.typesafe.akka" % "akka-kernel" % akkaVersion

    // val jUnit = "junit" % "junit" % "4.10" % "test"
  }

  object Resolvers {
    //val sonatypeNexusSnapshots = "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    //val sonatypeNexusStaging = "Sonatype Nexus Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    val typesafeReleasesRepository = "Typesafe Repo Releases" at "http://repo.typesafe.com/typesafe/releases/"
    val typesafeSnapshotsRepository = "Typesafe Repo Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
  }
}