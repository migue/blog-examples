package com.github.migue.monitoring.message.auth;

import com.github.migue.monitoring.message.Message;

/**
 * Messages representing authorization messages
 * 
 * @author Miguel Pastor
 */
final public class AuthMessage extends Message {
	public AuthMessage(String name, String user, String credentials) {
		super(System.currentTimeMillis());

		this.user = user;
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
	
		stringBuffer.append("User:");
		stringBuffer.append(this.user);

		return stringBuffer.toString();
	}
	
	private final String user;	
	private final String credentials;
}
