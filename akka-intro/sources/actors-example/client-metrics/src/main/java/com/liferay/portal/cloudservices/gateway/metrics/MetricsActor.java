package com.liferay.portal.cloudservices.gateway.metrics;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import com.github.migue.monitoring.message.MessageDefinition;

/**
* This actor is responsible for sending the metrics to the cloud service.
* 
* Once this actor is started it will send periodically metrics
* 
*/
public class MetricsActor extends UntypedActor {

	public MetricsActor(ActorRef endpoint) {
		_endpoint = endpoint;
	}

	public void onReceive(Object message) {
        String m = (String)message;

		if (MessageDefinition.METRIC_MESSAGE.equals(m)) {
            // TODO Collect metrics and send to the endpoint

            System.out.println("Collecting metrics and sending metrics . . .");

            _endpoint.tell(m);
        }
	}

	private ActorRef _endpoint;
}
