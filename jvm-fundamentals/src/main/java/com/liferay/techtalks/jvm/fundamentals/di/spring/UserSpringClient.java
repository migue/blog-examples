package com.liferay.techtalks.jvm.fundamentals.di.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liferay.techtalks.jvm.fundamentals.di.model.User;
import com.liferay.techtalks.jvm.fundamentals.di.service.DefaultUserService;
import com.liferay.techtalks.jvm.fundamentals.di.service.UserService;

public class UserSpringClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"com/liferay/techtalks/jvm/fundamentals/di/spring/applicationContext.xml");

		UserService userService = appContext.getBean(DefaultUserService.class);

		User user = userService.get(1);

		System.out.println("The user " + user.getUsername()
				+ " has been found!");

	}

}
