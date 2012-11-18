package com.github.migue.monitoring.message;

/**
 * Base class for all the metrics related messages.Every new message related to
 * metrics will need to extend that class.
 * 
 * @author Miguel Pastor
 */
public abstract class Metric extends Message {
	
	public Metric(String name) {
		super(System.currentTimeMillis());

		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	private final String name;
}
