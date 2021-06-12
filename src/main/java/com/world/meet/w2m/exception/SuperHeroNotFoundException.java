package com.world.meet.w2m.exception;

public class SuperHeroNotFoundException extends GenericException {


	private static long serialVersionUID = 5448077999646695145L;
	public static String ERROR_CODE = "COD01";
	public static String MESSAGE = "ERROR NOT FOUND ID";

	public SuperHeroNotFoundException(String message, String errorCode) {
		super(message, errorCode);
	}
}
