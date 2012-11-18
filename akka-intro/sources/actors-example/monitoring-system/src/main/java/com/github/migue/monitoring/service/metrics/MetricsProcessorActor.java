package com.github.migue.monitoring.service.metrics;

import akka.actor.UntypedActor;

import com.github.migue.monitoring.message.JVMMetric;
import com.github.migue.monitoring.service.datastore.DataStore;
import com.github.migue.monitoring.service.datastore.log.LogDataStore;

/**
 * This actor is reponsible for handling the metrics messages from the gateway
 * system
 */
public class MetricsProcessorActor extends UntypedActor {

	public void onReceive(Object message) {
        if (message instanceof JVMMetric) {
        	JVMMetric jvmMetric = (JVMMetric)message;
        	System.out.println("JVM Metrics received");
        	_dataStore.save(jvmMetric);
        }

	}

    private DataStore _dataStore = new LogDataStore();

}
