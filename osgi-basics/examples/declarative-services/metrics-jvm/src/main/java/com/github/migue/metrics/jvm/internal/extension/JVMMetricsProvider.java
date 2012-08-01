package com.github.migue.metrics.jvm.internal.extension;

import java.util.HashMap;
import java.util.Map;

import com.github.migue.metrics.api.model.Metric;
import com.github.migue.metrics.jvm.api.model.JVMMetric;
import com.github.migue.metrics.spi.MetricsProvider;

public class JVMMetricsProvider implements MetricsProvider {

	@Override
	public Metric collect() {
		return new JVMMetric();
	}

	@Override
	public Map<String, Object> getMetricProperties() {
		return new HashMap<String, Object>();
	}

	@Override
	public Long getId() {
		return 1L;
	}

}
