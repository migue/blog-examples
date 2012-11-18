package com.github.migue.monitoring.service.handshake;

import akka.actor.ActorRef;
import akka.util.Timeout;

import com.github.migue.monitoring.message.auth.AuthMessage;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.util.Duration;

import java.util.concurrent.TimeUnit;

/**
 * This actor is responsible for handling the handshake requests
 * 
 * @author Miguel Pastor
 * 
 */
public class HandshakeActor extends UntypedActor {

	public void onReceive(Object message) { 	

		if (message instanceof AuthMessage) {
            AuthMessage authMessage = (AuthMessage)message;

            if (authMessage.getUser() == "migue" && authMessage.getCredentials() == "migue")
            	getSender().tell("ok");	
            else
            	getSender().tell("fail");
         } else
            throw new IllegalStateException("Unable to handle " + message);
	}

}
