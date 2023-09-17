package com.dnb.ecommerce.advice;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.InvalidNameException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dnb.ecommerce.exception.ConstraintNotMatchException;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;

/**
 * Handling Custom and Predefine Exception 
 */
@ControllerAdvice
public class AppAdvice {
	
	// Handling IdNotFoundException
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler (IdNotFoundException e){
		Map<String, String> map = new HashMap<>();

		map.put("Message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND + "");

		return new ResponseEntity(map, HttpStatus.NOT_FOUND);

	}
	
	// Handling ConstraintNotMatchException
		@ExceptionHandler(ConstraintNotMatchException.class)
		public ResponseEntity<?> constraintNotMatchExceptionHandler (ConstraintNotMatchException e){
			Map<String, String> map = new HashMap<>();

			map.put("Message", e.getMessage());
			map.put("HttpStatus", HttpStatus.NOT_FOUND + "");

			return new ResponseEntity(map, HttpStatus.NOT_FOUND);

		}
	
	
	// Handling InvalidNameException
		@ExceptionHandler(InvalidNameException.class)
		public ResponseEntity<?> invalidNameExceptionHandler (InvalidNameException e){
			Map<String, String> map = new HashMap<>();

			map.put("Message", e.getMessage());
			map.put("HttpStatus", HttpStatus.NOT_FOUND + "");

			return new ResponseEntity(map, HttpStatus.NOT_FOUND);

		}
	
	// Handling InvalidIdException
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<?> invalidIdExceptionHandler (InvalidIdException e){
		Map<String, String> map = new HashMap<>();

		map.put("Message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND + "");

		return new ResponseEntity(map, HttpStatus.NOT_FOUND);

	}
	
	// Handling HttpRequestMethodNotSupportedException
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(HttpRequestMethodNotSupportedException e){
		
		String provided = e.getMethod();
		List<String> supported = List.of(e.getSupportedMethods());
		
		String error = provided 
				+ " is not one of the supported Http Method ("
				+ String.join(",", supported)
				+ ")";
				
				
		Map<String, String > errorResponse = Map.of(
				"error", error,
				"message", e.getLocalizedMessage(),
				"status", HttpStatus.METHOD_NOT_ALLOWED.toString());
				
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	
	}
	
	// Handling MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", LocalDateTime.now().toString());
        responseBody.put("status", status.value() + "");
        responseBody.put("errors", e.getFieldError().getDefaultMessage());

        return new ResponseEntity<>(responseBody, status);
    }
}
