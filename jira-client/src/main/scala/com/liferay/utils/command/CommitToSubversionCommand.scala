package com.liferay.utils.command

import com.atlassian.jira.rest.client.domain.Issue
import com.atlassian.jira.rest.client.domain.Transition
import com.atlassian.jira.rest.client.JiraRestClient
import com.atlassian.jira.rest.client.ProgressMonitor

class CommitToSubversionCommand(restClient: JiraRestClient, pm: ProgressMonitor) extends JiraCommand(restClient, pm) {

  override def execute(issueId: String) {
    val issue = retrieveIssue(issueId)

    val committedToSubversionTransition = findTransition(issue, "Commited to Subversion")

    println(committedToSubversionTransition)
    //restClient.getIssueClient().transition(issue.getTransitionsUri(), new TransitionInput(committedToSubversionTransition.getId()), pm);
  }

  protected def findTransition(issue: Issue, transitionName: String): List[Transition] = {
    restClient.getIssueClient().getTransitions(issue.getTransitionsUri(), pm).find(_.getName().equals(transitionName))
  }

}