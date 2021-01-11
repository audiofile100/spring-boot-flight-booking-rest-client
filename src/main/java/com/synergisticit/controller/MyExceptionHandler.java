package com.synergisticit.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler( { IOException.class, HttpClientErrorException.class } )
	public ResponseEntity<String> handleException(Exception e) {
		
		
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
	}
}
