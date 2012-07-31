package com.github.migue.metrics.api.model;

/**
 * Domain model representing the metrics of our system. All the metrics at our
 * system should extend this interface
 * 
 * @author migue
 * 
 */
public abstract class Metric {

	/**
	 * Return the metric name. Every metrics should have a unique name
	 * 
	 */
	public abstract String name();

	/**
	 * Indicate if the metric is enabled or disabled
	 * 
	 */
	public boolean isEnabled() {
		return _enabled;
	}

	private boolean _enabled;
}
