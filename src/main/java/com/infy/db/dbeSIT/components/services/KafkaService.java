package com.infy.db.dbeSIT.components.services;

import com.infy.db.dbeSIT.model.dto.NarConfigDTO;

public interface KafkaService {

	public String startTopic(String topic);
	
	public void processTopicData(String record);
	
}
