/**
 *
 */
package com.liferay.utils

import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory
import com.atlassian.jira.rest.client.JiraRestClient
import com.atlassian.jira.rest.client.NullProgressMonitor

import java.net.URI

class JiraCommandLauncher(private val jiraURI: String, private val username: String, val password: String) {

  /**
   *
   */
  def execute() {

    val factory = new JerseyJiraRestClientFactory();
    val jiraServerUri = new URI(jiraURI);
    val restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, username, password);
    val pm = new NullProgressMonitor();
  }
}

/**
 * @author migue
 *
 */
object JiraCommandLauncher extends App {

  if (args.length != 3) {
    println("Usage: JiraCommandLauncher <jira address> <username> <password>")
    System.exit(-1)
  }

  new JiraCommandLauncher(args(0), args(1), args(2)).execute()

}