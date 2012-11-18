package com.github.migue.monitoring.service.datastore.log;

import com.github.migue.monitoring.message.Message;
import com.github.migue.monitoring.service.datastore.DataStore;

public class LogDataStore implements DataStore {

	public void save(Message message) {
		System.out.println("Message:" + message);
	}
}
