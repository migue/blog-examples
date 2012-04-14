/**
 * 
 */
package com.liferay.techtalks.jvm.fundamentals.di.model;

/**
 * Class to model our domain
 * 
 * @author migue
 * 
 */
public class User {

	public User(long id) {
		this.id = id;
		this.username = Long.toString(id);
	}

	public String getUsername() {
		return this.username;
	}

	private long id;
	private String username;
}
