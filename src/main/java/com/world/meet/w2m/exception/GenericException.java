package com.world.meet.w2m.exception;

public class GenericException extends Exception {

	private static final long serialVersionUID = 5448077999646695145L;
	private String errorCode;

	public GenericException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

}
