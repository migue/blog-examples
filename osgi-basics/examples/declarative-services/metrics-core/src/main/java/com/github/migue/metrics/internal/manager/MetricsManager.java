package com.github.migue.metrics.internal.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.github.migue.metrics.api.model.Metric;
import com.github.migue.metrics.spi.MetricsProvider;

/**
 * Component responsible for the handling and registering of all metrics
 * providers.
 * 
 * Asking for all the metrics will collect all the providers and return all the
 * collected metrics
 */
public class MetricsManager {

	@Override
	void activate(Map properties) { {
		System.out.println("Starting the metrics manager. Waiting for metrics providers . . .");

		_executorService.execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// run forever and collect metrics every 1 seconds

					List<Metric> metrics = collect();

					for (Metric metric : metrics) {
						System.out.println(metric);
					}

					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		_executorService.shutdown();
	}

	protected List<Metric> collect() {
		List<Metric> metrics = new ArrayList<Metric>();

		// Approach one:implementing service listeners: depends on our needs

		// Collection<ServiceReference<MetricsProvider>> serviceReferences =
		// _bundleContext
		// .getServiceReferences(MetricsProvider.class, null);

		// using a service tracker

		Object[] metricsProviders = _serviceTracker.getServices();

		// Collect all the provided metrics: if defined
		if (metricsProviders == null) {
			return new ArrayList<Metric>();
		}

		for (int i = 0; i < metricsProviders.length; ++i) {
			MetricsProvider metricsProvider = (MetricsProvider) metricsProviders[i];

			metrics.add(metricsProvider.collect());
		}

		return metrics;
	}

	private Map<

	private ExecutorService _executorService = Executors.newCachedThreadPool();

}