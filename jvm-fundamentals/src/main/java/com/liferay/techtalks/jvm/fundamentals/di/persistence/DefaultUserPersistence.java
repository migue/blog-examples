package com.liferay.techtalks.jvm.fundamentals.di.persistence;

import org.springframework.stereotype.Repository;

import com.liferay.techtalks.jvm.fundamentals.di.model.User;

/**
 * Default implementation for the {@link UserPersistence}
 * 
 * @author migue
 * 
 */
@Repository
public class DefaultUserPersistence implements UserPersistence {

	public User get(long id) {
		return new User(id);
	}

	public void save(User user) {
		System.out.println("Saving the user on a persistent datastore");
	}

}
