package com.myspring.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myspring.dto.ApiResponseMessage;

import ch.qos.logback.core.status.Status;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	//Handle resource not found exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage>  resourceNotFoundException(ResourceNotFoundException ex){
		log.info("Exception Handler Invoked : {}",ex.getMessage());
		ApiResponseMessage response  = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.success(true)
				.status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<ApiResponseMessage>(response,HttpStatus.OK);
	}

	//Handle sql primary Key  Violation exception
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponseMessage>  handelerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex){
		log.info("Exception Handler Invoked : {}",ex.getMessage());
		ApiResponseMessage response  = ApiResponseMessage.builder()
				.message(ex.getMessage())
				.success(true)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		return new ResponseEntity<ApiResponseMessage>(response,HttpStatus.BAD_REQUEST);
	}
	
	//Handle Method Arguments exception
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String, Object>>  handelerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
			log.info("Exception Handler Invoked : {}",ex.getMessage());
			List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
            Map<String, Object> response  = new  HashMap<>();
            
            allErrors.forEach(objectError->{
            	String message = objectError.getDefaultMessage();
            	String field = ((FieldError)objectError).getField();
            	response.put(field, response);
            	
            });
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	
	
}
