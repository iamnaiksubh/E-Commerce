package com.dnb.ecommerce.exception;

public class IdNotFoundException extends Exception{
	
	public IdNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
