package com.liferay.utils.command

import com.atlassian.jira.rest.client.domain.Issue
import com.atlassian.jira.rest.client.JiraRestClient
import com.atlassian.jira.rest.client.ProgressMonitor

/**
 * Base class for jira commands.
 *
 * It is modeled as an abstract class because Java interoperability
 */
abstract class JiraCommand(private val restClient: JiraRestClient, private val pm: ProgressMonitor) {

  def retrieveIssue(issueId: String) : Issue = {
    restClient.getIssueClient().getIssue(issueId, pm)    
  }

  def execute(issueId: String);
}