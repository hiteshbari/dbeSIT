package com.infy.db.dbeSIT.components.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.db.dbeSIT.components.services.KafkaService;

@RestController
@RequestMapping("/dbeSIT/kafka")
public class KafkaController {

	@Autowired
	KafkaService kafkaService;
	
	@PostMapping(value = "/startSITTopic", produces = "application/json")
	public ResponseEntity<String> startSITTopic(@RequestParam("topicName") String topicName){
		kafkaService.startSITTopic(topicName);
		return new ResponseEntity<String>("DONE", HttpStatus.CREATED);
	}
	
}
