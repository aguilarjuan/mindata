package com.world.meet.w2m.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto<T> implements Serializable
{
	private T data;
	private ErrorDto error;
	private String statusCode;

	public ResponseDto(T data, ErrorDto error, String statusCode)
	{
		this.data = data;
		this.error = error;
		this.statusCode = statusCode;
	}

	public ResponseDto()
	{
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public ErrorDto getError()
	{
		return error;
	}

	public void setError(ErrorDto error)
	{
		this.error = error;
	}

	public String getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	@Override public String toString()
	{
		return "ResponseDto{" + "data=" + data + ", error=" + error + ", statusCode='" + statusCode + '\'' + '}';
	}
}
