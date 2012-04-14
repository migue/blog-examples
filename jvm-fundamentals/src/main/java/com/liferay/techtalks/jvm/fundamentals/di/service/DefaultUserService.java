package com.liferay.techtalks.jvm.fundamentals.di.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.liferay.techtalks.jvm.fundamentals.di.model.User;
import com.liferay.techtalks.jvm.fundamentals.di.persistence.UserPersistence;

/**
 * Default implementation for the {@link UserPersistence}
 * 
 * @author migue
 * 
 */
@Service
public class DefaultUserService implements UserService {

	public User get(long id) {
		return this.userPersistence.get(id);
	}

	public void save(User user) {
		this.userPersistence.save(user);
	}

	@Inject
	protected UserPersistence userPersistence;

}
