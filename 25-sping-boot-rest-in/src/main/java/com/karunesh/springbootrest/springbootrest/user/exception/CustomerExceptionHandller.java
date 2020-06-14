package com.karunesh.springbootrest.springbootrest.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karunesh.springbootrest.springbootrest.exception.UserErrorMessage;
import com.karunesh.springbootrest.springbootrest.exception.UserNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandller {
	
	@ExceptionHandler
	public ResponseEntity<UserErrorMessage> handleUserNotFound(UserNotFoundException ex) {
		UserErrorMessage error = 
				new UserErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());		
		return new ResponseEntity<UserErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<UserErrorMessage> handleGenericMethod(MethodArgumentNotValidException ex) {
		UserErrorMessage error = 
				new UserErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage());		
		return new ResponseEntity<UserErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorMessage> handleGeneric(Exception ex) {
		UserErrorMessage error = 
				new UserErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());		
		return new ResponseEntity<UserErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	
	
	
	

}
