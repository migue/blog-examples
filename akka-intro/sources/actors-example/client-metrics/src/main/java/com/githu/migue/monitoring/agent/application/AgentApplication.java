package com.githu.migue.monitoring.agent.application;

import java.util.concurrent.TimeUnit;

import scala.parallel.Future;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;
import akka.kernel.Bootable;
import akka.util.Timeout;

import com.github.migue.monitoring.message.MessageDefinition;
import com.liferay.portal.cloudservices.gateway.metrics.MetricsActor;

public class AgentApplication implements Bootable {

	public void shutdown() {
		_agentSystem.shutdown();
	}

	public void startup() {

        System.out.println("Starting the client metrics agent");


        // 1. Connect to the monitoring system

        _doInitialHandshake();

		// 2. Start the metrics actor

		_configureMetricsActor();
	}

    private void _doInitialHandshake() {
        final Timeout timeout = new Timeout(Duration.create(10, TimeUnit.SECONDS));

        ActorRef handshakeActor = _agentSystem.actorFor("akka://MonitoringServiceSystem@localhost:5557/user/handshakeActor");

        Future<Object> handshake = ask(handshakeActor, new AuthMessage("migue","migue"), timeout);

        // Do it in a synchronous way

        try {            
            Await.result(handshake, Duration.create(10, TimeUnit.SECONDS));
        }
        catch(Exception ate) {
            System.out.println("Error while handshaking with the monitoring system!!!");
            ate.printStackTrace();
            System.exit(-1);
        }

    }

    private void _configureMetricsActor() {    	
    	ActorRef metricsActor = context.actorFor("akka://MonitoringServiceSystem@localhost:5557/user/metricsActor");

		_metrics = _agentSystem.actorOf(new Props(new UntypedActorFactory() {
				@Override
				public Actor create() {
					return new MetricsActor(metricsActor);
				}
			}), "metricsActor");

        // Every second the metrics actor will receive a message telling it to collect info        
        _agentSystem.scheduler().schedule(
                Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), _metrics, MessageDefinition.METRIC_MESSAGE);    	
    }

	// Local actor responsible for sending messages to the remote endpoint

	private ActorSystem _agentSystem = ActorSystem.create("AgentSystem");

	private ActorRef _metrics = null, _monitoringSystem = null;
	
	/**
	* TODO For this prototype it acts as the gateway system.
	*/
	public static void main(String[] args) throws InterruptedException {
		new AgentApplication().startup();
	}

}
