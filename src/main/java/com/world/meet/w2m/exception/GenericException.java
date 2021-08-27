package com.world.meet.w2m.exception;

import com.world.meet.w2m.dto.ErrorDto;
import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.dto.SuperHeroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

	public static ResponseEntity<?> GenericExceptionResponse(GenericException e){
		ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
		ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
		responseDto.setError(errorDto);
		responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
