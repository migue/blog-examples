package com.liferay.techtalks.jvm.fundamentals.di.service;

import com.liferay.techtalks.jvm.fundamentals.di.model.User;

/**
 * Simple interface to model the user business logic.
 * 
 * <p>
 * This is a stupid interface to show how dependency injection works
 * </p>
 * 
 * @author migue
 * 
 */
public interface UserService {

	User get(long id);
	
	void save(User user);
	
}
