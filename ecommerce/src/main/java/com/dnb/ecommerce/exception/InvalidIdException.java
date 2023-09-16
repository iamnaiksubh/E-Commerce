package com.dnb.ecommerce.exception;

public class InvalidIdException extends Exception{
	public InvalidIdException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
