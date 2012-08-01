package com.github.migue.metrics.spi;

import java.util.Map;

import com.github.migue.metrics.api.model.Metric;

/**
* SPI for metrics extensions
*/
public interface MetricsProvider {

	public Metric collect();

	public Map<String, Object> getMetricProperties();

	public Long getId();

}