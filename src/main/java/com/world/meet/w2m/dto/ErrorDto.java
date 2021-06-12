package com.world.meet.w2m.dto;


public class ErrorDto {

	private String message;
	private String codeError;

	public ErrorDto(String message, String codeError)
	{
		this.message = message;
		this.codeError = codeError;
	}

	public ErrorDto()
	{
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getCodeError()
	{
		return codeError;
	}

	public void setCodeError(String codeError)
	{
		this.codeError = codeError;
	}

	@Override public String toString()
	{
		return "ErrorDto{" + "message='" + message + '\'' + ", codeError='" + codeError + '\'' + '}';
	}
}
