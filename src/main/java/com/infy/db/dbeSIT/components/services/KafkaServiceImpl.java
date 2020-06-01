package com.infy.db.dbeSIT.components.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.db.dbeSIT.model.dto.NarConfigDTO;
import com.infy.db.dbeSIT.util.DBESITUtil;

@Service
public class KafkaServiceImpl implements KafkaService {

	@Autowired
	DbeRequestService dbeRequestService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public String startTopic(String topic) {
		String rtn = DBESITUtil.RETURN_DEF;
		KafkaConsumer<String, String> kafkaConsumer = KafkaConsumerImpl.getNEWKafkaConsumer("myConsumerSIT");
		kafkaConsumer.subscribe(Arrays.asList(topic));
		final int NoOfTries = 5;
		int RecordsCount = 0;
		kafkaConsumer.poll(0); // Initiate the consumer
		kafkaConsumer.seekToBeginning(kafkaConsumer.assignment());// Start consuming messages from beginning.
		while (true) {
			final ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(30000);
			if (consumerRecords.count() == 0) {
				RecordsCount++;
				if (RecordsCount > NoOfTries)
					break;
				else
					continue;
			}
			ConsumerRecord<String, String> CSRecord = null;
			for (Iterator<ConsumerRecord<String, String>> record = consumerRecords.iterator(); record.hasNext();) {
				CSRecord = record.next();
				/*
				 * System.out.printf("Consumer Record: Key - " + CSRecord.key() + " Value - " +
				 * CSRecord.value() + " Partition - " + CSRecord.partition() + " Offset - " +
				 * CSRecord.offset() + "\n");
				 */
				processTopicData(CSRecord.value());
			}
			CSRecord = null; // garbage collection assist.
			kafkaConsumer.commitAsync();
		}
		kafkaConsumer.close();
		return rtn;
	}

	public void processTopicData(String record) {
		try {
			
			//String s = "{\"reqId\": \"NS1726456C0FF\",\"narId\": \"10014-1\",\"polEditors\": [\"10014-1\"],\"polApprovers\": [\"10014-1\"],\"dbeEnv\": \"UAT\"}";
			NarConfigDTO narConfigDTO = mapper.readValue(record, NarConfigDTO.class);
			dbeRequestService.createNarConfig(narConfigDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
