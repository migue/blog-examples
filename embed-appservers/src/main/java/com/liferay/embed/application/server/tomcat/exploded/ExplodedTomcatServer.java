/**
 *
 */
package com.liferay.embed.application.server.tomcat.exploded;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;

import com.liferay.embed.application.server.EmbeddableApplicationServer;
import com.liferay.embed.application.server.tomcat.TomcatServer;

/**
 * Runs the portal witouth war packaging; using the classes present in the
 * development environment
 * 
 * @author Miguel Pastor
 * 
 */
public class ExplodedTomcatServer extends TomcatServer {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("Expected args: <Working Dir><Webapp Base Dir>");
			System.exit(-1);
		}

		EmbeddableApplicationServer tomcatInstance = new ExplodedTomcatServer(
				args[0], args[1], null);

		try {
			tomcatInstance.start();
		} catch (Exception e) {
			// TODO: handle exception in a better way
			e.printStackTrace();
		}

	}

	public ExplodedTomcatServer(String workDir, String baseDir,
			ClassLoader classLoader) {

		super(workDir);

		try {
			Context liferayContext = _tomcat.addWebapp(
					EmbeddableApplicationServer.LIFERAY_DEFAULT_CONTEXT,
					baseDir);

			// configure the context
			liferayContext.setReloadable(true);

			// TODO we could write a custom loader
			WebappLoader loader = new WebappLoader(classLoader);
			liferayContext.setLoader(loader);

		} catch (ServletException e) {
			System.err.println("Include logging:" + e.getStackTrace());
		}

	}

	@Override
	public void stop() {
		try {
			_tomcat.stop();
		} catch (LifecycleException e) {
			// TODO Include logs
			e.printStackTrace();
		}

	}

	@SuppressWarnings("all")
	@Override
	protected void doStart(String hostName, int port) {

		try {
			if (hostName != "localhost") {
				_tomcat.getConnector().setAttribute("address", hostName);
			}

			_tomcat.setPort(port);

			_tomcat.start();
			_tomcat.getServer().await();

		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private final Tomcat _tomcat = new Tomcat();
}