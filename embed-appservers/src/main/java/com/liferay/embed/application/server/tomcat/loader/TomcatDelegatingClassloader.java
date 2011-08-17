package com.liferay.embed.application.server.tomcat.loader;

import java.net.MalformedURLException;
import java.net.URL;

public class TomcatDelegatingClassloader extends ClassLoader {

	public TomcatDelegatingClassloader(ClassLoader parent, String[] classpath) {
		super(parent);

		_resources = classpath;
	}

	@Override
	protected java.net.URL findResource(String name) {
		for (String resource : _resources) {
			if (resource.equals(name)) {
				try {
					return new URL(name);
				} catch (MalformedURLException e) {
					// TODO Add logging
					e.printStackTrace();
				}
			}
		}

		return null;
	};

	@Override
	protected java.util.Enumeration<java.net.URL> findResources(String name)
			throws java.io.IOException {
		return null;
	};

	private String[] _resources;
}