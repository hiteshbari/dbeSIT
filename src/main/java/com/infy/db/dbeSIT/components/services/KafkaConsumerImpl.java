package com.infy.db.dbeSIT.components.services;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaConsumerImpl {

	private final static String BOOTSTRAP_SERVERS = "localhost:9092";
	private KafkaConsumer<String, String> kafkaConsumer;
	
	private ObjectMapper mapper = new ObjectMapper();
	public KafkaConsumerImpl(String consumerName) {
		super();
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, consumerName);
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		kafkaConsumer = new KafkaConsumer<String,String>(prop);
	}

	public KafkaConsumer<String, String> getKafkaConsumer() {
		return kafkaConsumer;
	}

	public static KafkaConsumer<String, String> getNEWKafkaConsumer(String consumerName) {
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, consumerName);
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		return new KafkaConsumer<String,String>(prop);
	}
}
