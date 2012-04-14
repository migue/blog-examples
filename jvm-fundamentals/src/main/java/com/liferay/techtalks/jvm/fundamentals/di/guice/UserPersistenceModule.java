package com.liferay.techtalks.jvm.fundamentals.di.guice;

import com.google.inject.AbstractModule;
import com.liferay.techtalks.jvm.fundamentals.di.persistence.DefaultUserPersistence;
import com.liferay.techtalks.jvm.fundamentals.di.persistence.UserPersistence;

public class UserPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserPersistence.class).to(DefaultUserPersistence.class);

	}

}
