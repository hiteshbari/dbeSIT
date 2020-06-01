package com.infy.db.dbeSIT.components.services;

import com.infy.db.dbeSIT.model.dto.NarConfigDTO;

public interface KafkaService {

	public String startSITTopic(String sitTopic);
	
	public void processTopicData(String record);
	
}
