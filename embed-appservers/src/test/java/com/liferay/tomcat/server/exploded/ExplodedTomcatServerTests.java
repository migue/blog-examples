package com.liferay.tomcat.server.exploded;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.liferay.embed.application.server.EmbeddableApplicationServer;
import com.liferay.embed.application.server.tomcat.exploded.ExplodedTomcatServer;

/**
 * Unit tests suite in order to validate the behaviour of the Tomcat
 * 
 * @author Miguel Pastor
 * 
 */
public class ExplodedTomcatServerTests {

	@After
	public void stopTomcatContainer() {
		try {
			_tomcatServer.stop();
		} catch (Exception e) {
			// Do nothing: in some tests this could be called twice
		}

	}

	@Test
	public void startLiferayApp() {
		try {
			_tomcatServer.start();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unexpected error");
		}

	}

	private EmbeddableApplicationServer _tomcatServer = new ExplodedTomcatServer(
			"",
			"/home/migue/development/workspaces/workspace-liferayportal/Archive/portal/portal-web/docroot/",
			ExplodedTomcatServer.class.getClassLoader());
}
