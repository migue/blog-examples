name := "jvm-fundamentals"

description := "Some Java 7 and JVM fundamentals"

version := "0.1"

libraryDependencies ++= Seq(
 "com.google.inject" % "guice" % "3.0-rc3",
 "org.springframework" % "spring-context" % "3.1.0.RELEASE",
 "junit" % "junit" % "4.10" % "test"
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"