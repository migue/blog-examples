package com.liferay.portal.kernel.servlet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests suite in order to validate the behaviour of the Tomcat
 * 
 * @author Miguel Pastor
 * 
 */
public class ExplodedTomcatStylesResolutionTests {

	@Test
	public void testRootDirReplacement() {
		try {
			String rootDir = WebDirDetector.getRootDir(null);
			Assert.assertEquals(
					"/home/migue/development/workspaces/workspace-liferayportal/Archive/portal/portal-web/docroot/",
					rootDir);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unexpected error");
		}

	}

}
