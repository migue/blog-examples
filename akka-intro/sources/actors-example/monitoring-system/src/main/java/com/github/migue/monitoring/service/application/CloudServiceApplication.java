package com.github.migue.monitoring.service.application;

import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.kernel.Bootable;
import akka.routing.RoundRobinRouter;

import com.github.migue.monitoring.service.handshake.HandshakeActor;
import com.github.migue.monitoring.service.metrics.MetricsProcessorActor;


public class CloudServiceApplication implements Bootable {

	public void shutdown() {
		_monitoringServiceSystem.shutdown();
	}

	public void startup() {

        // 1. Start the actor responsible of the handshake

        _handshakeActor = _monitoringServiceSystem.actorOf(new Props(HandshakeActor.class), "handshakeActor");

		// 2. Start the actor responsible of the metrics handling

		final SupervisorStrategy supervisorStrategy =
                new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES), new Class<?>[] {Exception.class});

        _metricsActor = _cloudServiceSystem.actorOf(
                new Props(MetricsProcessorActor.class).withRouter(
                        new RoundRobinRouter(100).withSupervisorStrategy(
                                supervisorStrategy)), "metricsActor");
	}
	
	private ActorRef _handshakeActor = null, _metricsActor = null;
	
	private final ActorSystem _monitoringServiceSystem = ActorSystem.create("MonitoringServiceSystem");
	
	/**
	* TODO Acts as the deployed app. We should decide how to deploy it!
	* Microkernel based could be a reasonable option
	*/
	public static void main(String[] args) {
		new CloudServiceApplication().startup();

		System.out.println("Started the system actor endpoint (over http). Waiting for gateway metrics.");
	}
}
