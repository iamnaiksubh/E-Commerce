package com.dnb.ecommerce.exception;

/**
 * Handling Constraint/Business Validations Not Matching Exception
 */
public class ConstraintNotMatchException extends Exception {
	
	public ConstraintNotMatchException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
