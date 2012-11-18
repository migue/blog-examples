package com.github.migue.monitoring.message;

/**
 * Base class for all the messages. Marker class
 * 
 * @author Miguel Pastor
 */
public abstract class Message {
	public Message(long timestamp) {
		this.timestamp = timestamp;
	}

	private final long timestamp;
}
