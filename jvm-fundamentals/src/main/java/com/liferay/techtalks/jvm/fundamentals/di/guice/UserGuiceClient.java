package com.liferay.techtalks.jvm.fundamentals.di.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.liferay.techtalks.jvm.fundamentals.di.model.User;
import com.liferay.techtalks.jvm.fundamentals.di.service.DefaultUserService;
import com.liferay.techtalks.jvm.fundamentals.di.service.UserService;

public class UserGuiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new UserPersistenceModule());

		UserService userService = injector
				.getInstance(DefaultUserService.class);

		User user = userService.get(1);

		System.out.println("The user " + user.getUsername()
				+ " has been found!");

	}

}
