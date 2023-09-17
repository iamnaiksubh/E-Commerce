package com.dnb.ecommerce.exception;

/**
 * Handling Invalid Name Exception
 */

public class InvalidNameException extends Exception {
	
	public InvalidNameException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
