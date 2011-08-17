/**
 * 
 */
package com.liferay.embed.application.server.tomcat.classloading;

/**
 * @author Miguel Pastor
 * 
 */
public aspect ExplodedTomcatStylesResolutor {

	@SuppressWarnings("nls")
	public pointcut StylesResolutor() : execution(String com.liferay.portal.kernel.servlet.WebDirDetector.getRootDir(java.lang.ClassLoader));

	String around(): StylesResolutor() {
		System.out
				.println("Exploded Tomcat Mode: retrieving styles in a safe way");

		return _baseStylesLibdir;
	}

	// TODO Review this configuration in order to make it configurable by the
	// user
	private String _baseStylesLibdir = "/home/migue/development/workspaces/workspace-liferayportal/Archive/portal/portal-web/docroot/";
}
