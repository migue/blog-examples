package com.liferay.embed.application.server.tomcat.loader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.loader.WebappLoader;

/**
 * Tomcat loader to monitor class folders and react to changes (redeploy the context)
 * 
 * @author migue
 * 
 */
public class ExplodedTomcatLoader extends WebappLoader {

	public ExplodedTomcatLoader() {
		super();
	}

	public ExplodedTomcatLoader(ClassLoader classLoader) {
		super(classLoader);
	}

	@Override
	public boolean modified() {

		boolean modified = super.modified();

		if (!modified) {
			// TODO check for changes in the monitored folders
			
		}

		return modified;
	}

	private Map<String, Date> modifiedFiles = new HashMap<String, Date>();
}