/**
 *
 */
package com.liferay.embed.application.server.tomcat;

import java.io.File;

import com.liferay.embed.application.server.EmbeddableApplicationServer;

/**
 * 
 * @author Miguel Pastor
 * 
 */
public abstract class TomcatServer implements EmbeddableApplicationServer {

	public TomcatServer(String workingPath) {
		// project workspace, make it configurable ??
		workingDirectory = new File(workingPath);

		tomcatDirectory = new File(workingDirectory, "tomcat");

		tomcatDirectory.delete();
	}

	@Override
	public void restart() {
		stop();
		start();
	}

	@Override
	public void start() {
		start(null, null);
	}

	@Override
	public void start(Integer port) {
		start(null, port);

	}

	@Override
	public void start(String hostName, Integer port) {
		String host = hostName != null ? hostName : DEFAULT_HOST;
		Integer p = port != null ? port : DEFAULT_PORT;

		this.doStart(host, p);
	}

	public abstract void stop();

	protected abstract void doStart(String hostName, int port);

	protected File workingDirectory;

	protected File tomcatDirectory;

}