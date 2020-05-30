package com.infy.db.dbeSIT.components.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CustomJsonConversionEx extends JsonProcessingException {
	
	private static final long serialVersionUID = 1L;

	public CustomJsonConversionEx(String msg) {
		super(msg);
	}
}
