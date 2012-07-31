package com.github.migue.metrics.jvm.internal.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.github.migue.metrics.jvm.internal.extension.JVMMetricsProvider;
import com.github.migue.metrics.spi.MetricsProvider;

public class JVMMetricsExtension implements BundleActivator {

	public void start(BundleContext bundleContext) {
		_serviceRegistration = bundleContext
				.registerService(MetricsProvider.class.getName(),
						new JVMMetricsProvider(), null);
		
	}

	public void stop(BundleContext bundleContext) {
		_serviceRegistration.unregister();
	}

	private ServiceRegistration _serviceRegistration;
}