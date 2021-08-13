package com.dairy.managment.app.controller.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import com.dairy.managment.app.rest.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {OperationNotSupportedException.class})
	public ResponseEntity<ErrorResponse> handleOperationNotSupported(ServletWebRequest request, OperationNotSupportedException exception){
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(new Date());
		response.setStatus(409);
		response.setError(exception.getMessage());	
		response.setPath(request.getRequest().getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
	public ResponseEntity<ErrorResponse> handleIntegrityConstraintViolation(ServletWebRequest request, SQLIntegrityConstraintViolationException exception){
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(new Date());
		response.setStatus(409);
		response.setError(exception.getMessage());	
		response.setPath(request.getRequest().getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(ServletWebRequest request, MethodArgumentNotValidException exception){
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(new Date());
		response.setStatus(400);
		
		List<String> errorList = new ArrayList<>();		
		List<FieldError> fieldErrors = exception.getFieldErrors();
		fieldErrors.forEach(error -> errorList.add(error.getField() +" "+ error.getDefaultMessage()));
		exception.getBindingResult();
		response.setError(errorList.toString());	
		response.setPath(request.getRequest().getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
