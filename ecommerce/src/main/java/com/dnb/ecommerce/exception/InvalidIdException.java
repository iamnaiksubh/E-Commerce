package com.dnb.ecommerce.exception;

/**
 * Handling Invalid Id Exception
 */

public class InvalidIdException extends Exception{
	public InvalidIdException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
