package com.liferay.techtalks.jvm.fundamentals.di.persistence;

import com.liferay.techtalks.jvm.fundamentals.di.model.User;

/**
 * Simple interface to model the user pesistence.
 * 
 * <p>
 * This is a stupid interface to show how dependency injection works
 * </p>
 * 
 * @author migue
 * 
 */
public interface UserPersistence {

	User get(long id);
	
	void save(User user);
	
}
