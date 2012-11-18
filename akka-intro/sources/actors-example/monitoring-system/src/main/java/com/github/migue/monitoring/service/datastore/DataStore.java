package com.github.migue.monitoring.service.datastore;

import com.github.migue.monitoring.message.Message;

public interface DataStore {

	void save(Message message);
}
