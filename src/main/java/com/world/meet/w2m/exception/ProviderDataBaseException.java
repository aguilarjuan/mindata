package com.world.meet.w2m.exception;

public class ProviderDataBaseException extends GenericException
{

	private static final long serialVersionUID = 5448077999646695145L;
	public static String ERROR_CODE = "COD02";
	public static String MESSAGE = "BD CONNECTION ERROR";

	public ProviderDataBaseException(String message, String errorCode) {
		super(message, errorCode);

	}

}
