package com.github.migue.metrics.internal.manager.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class MetricsProviderCustomizer implements ServiceTrackerCustomizer {

	public MetricsProviderCustomizer(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		// TODO Auto-generated method stub
		
	}

	private final BundleContext _bundleContext;

}
