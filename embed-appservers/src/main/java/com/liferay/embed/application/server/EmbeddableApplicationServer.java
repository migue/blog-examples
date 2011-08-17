/**
 * 
 */
package com.liferay.embed.application.server;

/**
 * Specification for all the Liferay container used under development
 * 
 * @author Miguel Pastor
 * 
 */
public interface EmbeddableApplicationServer {

	public static final Integer DEFAULT_PORT = Integer.valueOf(8080);

	public static final String DEFAULT_HOST = "localhost";

	public static final String LIFERAY_DEFAULT_CONTEXT = "/";

	/**
	 * Restart the container (usually by calling stop/start)
	 */
	void restart();

	/**
	 * Start the container at the defaults values for host and port
	 * 
	 * @see #DEFAULT_HOST
	 * @see #DEFAULT_PORT
	 */
	void start();

	/**
	 * Start the container at the specified port
	 * 
	 * @param port
	 *            Port number where the container will be listening for the
	 *            requests
	 */
	void start(Integer port);

	/**
	 * Start the container at the specified host and port
	 * 
	 * @param hostName
	 *            Name of the host
	 * @param port
	 *            Port number where the container will be listening for the
	 *            requests
	 */
	void start(String hostName, Integer port);

	/**
	 * Stops the running container
	 */
	void stop();

}
