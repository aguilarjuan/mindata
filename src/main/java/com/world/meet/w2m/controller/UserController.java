package com.world.meet.w2m.controller;

import com.world.meet.w2m.dto.ErrorDto;
import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.dto.UserDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
public class UserController
{

	@Autowired
	private UserService userService;

	@PostMapping(path = "/token")
    public ResponseEntity<?> validationUser(@RequestParam("user") String username, @RequestParam("password") String pwd){
        ResponseDto responseDto = new ResponseDto();
		try
		{
			UserDto dto  = this.userService.validateUser(username,pwd);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());

			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e)
		{
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
