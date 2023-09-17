package com.dnb.ecommerce.exception;

/**
 * Handling Id Not Found Exception 
 */

public class IdNotFoundException extends Exception{
	
	public IdNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
