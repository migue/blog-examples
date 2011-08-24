#!/bin/bash

echo "Starting Liferay Portal in Tomcat 7.0.16 (exploded mode). Type Ctrl + C to stop it!"

## Base path of the project
PROJECT_HOME=/home/migue/development/workspaces/workspace-liferayportal/Archive/portal

## Paths to portal's dependencies

DEVELOPMENT_LIBRARIES="${PROJECT_HOME}/lib/development/persistence.jar:${PROJECT_HOME}/lib/development/jta.jar:${PROJECT_HOME}/lib/development/mysql.jar:${PROJECT_HOME}/lib/development/mail.jar"
PORTAL_LIBRARIES=${PROJECT_HOME}/lib/portal/*
GLOBAL_LIBRARIES=${PROJECT_HOME}/lib/global/*

## Paths to compiled code

PORTAL_IMPL_CLASSES=${PROJECT_HOME}/portal-impl/classes/
PORTAL_SERVICE_CLASSES=${PROJECT_HOME}/portal-service/classes/
PORTAL_UTIL_BRIDGES_CLASSES=${PROJECT_HOME}/util-bridges/classes/
PORTAL_UTIL_JAVA_CLASSES=${PROJECT_HOME}/util-java/classes/
PORTAL_UTIL_TAGLIB_CLASSES=${PROJECT_HOME}/util-taglib/classes/

## CLASSPATH definition
MAIN_JAR="target/embed-tomcat-0.0.1.SNAPSHOT.jar"

CLASSPATH="${MAIN_JAR}:target/dependencies/*:${PORTAL_LIBRARIES}:${GLOBAL_LIBRARIES}:${DEVELOPMENT_LIBRARIES}:${PORTAL_IMPL_CLASSES}:${PORTAL_SERVICE_CLASSES}:${PORTAL_UTIL_BRIDGES_CLASSES}:${PORTAL_UTIL_JAVA_CLASSES}:${PORTAL_UTIL_TAGLIB_CLASSES}"

## Load Time Weaving Agent

LTW_AGENT_PATH="ltw-agent/aspectjweaver-1.6.11.M2.jar"
LTW_AGENT_OPTION="-javaagent:${LTW_AGENT_PATH}"

## Debug options (run.sh debug)

if [ "$1" = "debug" ]
then
	echo "Starting Liferay in DEBUG mode"
	DEBUG_OPTIONS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9000"
fi

## JVM options

JVM_OPTS="${LTW_AGENT_OPTION} ${DEBUG_OPTIONS} -Xms256m -Xmx1024m -XX:MaxPermSize=256m"

## Run the application server

java ${JVM_OPTS} -cp ${CLASSPATH} com.liferay.embed.application.server.tomcat.exploded.ExplodedTomcatServer /tmp/exploded-tomcat/ /home/migue/development/workspaces/workspace-liferayportal/Archive/portal/portal-web/docroot/
