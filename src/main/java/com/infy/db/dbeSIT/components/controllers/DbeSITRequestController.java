package com.infy.db.dbeSIT.components.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.db.dbeSIT.components.exceptions.CustomJsonConversionEx;
import com.infy.db.dbeSIT.components.services.DbeRequestService;
import com.infy.db.dbeSIT.components.services.KafkaService;
import com.infy.db.dbeSIT.model.dto.NarConfigDTO;

@RestController
@RequestMapping("/dbeSIT/request")
public class DbeSITRequestController {

	@Autowired
	DbeRequestService dbeRequestService;
	
	@PostMapping(value = "/createNarConfig", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createNarConfig(@RequestBody NarConfigDTO narConfigDTO) throws CustomJsonConversionEx{
		dbeRequestService.createNarConfig(narConfigDTO);
		return new ResponseEntity<String>("DONE", HttpStatus.CREATED);
	}
}
