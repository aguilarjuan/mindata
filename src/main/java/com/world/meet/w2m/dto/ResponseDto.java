package com.world.meet.w2m.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> implements Serializable
{
	private T data;
	private ErrorDto error;
	private String statusCode;
}
