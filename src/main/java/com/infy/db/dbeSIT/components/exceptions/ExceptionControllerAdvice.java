package com.infy.db.dbeSIT.components.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(CustomDataNotFoundEx.class)
	public ResponseEntity<CustomErrorMsg> nullfromDatabase(CustomDataNotFoundEx ex){
		CustomErrorMsg eMsg = new CustomErrorMsg();
		eMsg.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		eMsg.setErrorMessage(ex.getMessage());
		return new ResponseEntity<CustomErrorMsg>(eMsg,HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomJsonConversionEx.class)
	public ResponseEntity<CustomErrorMsg> jsonProcessException(CustomJsonConversionEx ex){
		CustomErrorMsg eMsg = new CustomErrorMsg();
		eMsg.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		eMsg.setErrorMessage(ex.getMessage());
		return new ResponseEntity<CustomErrorMsg>(eMsg,HttpStatus.OK);
	}
}
