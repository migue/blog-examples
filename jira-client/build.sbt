name := "Jira Client"

version := "0.1"

// Add a single dependency
libraryDependencies ++= Seq(
		"com.atlassian.jira" % "jira-rest-java-client" % "0.3.1"
)

// resolver
resolvers += "Jira Maven repository" at "https://maven.atlassian.com/content/groups/public/"

scalaVersion := "2.9.0-1"

