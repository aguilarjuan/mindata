package com.world.meet.w2m.service;

import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.dto.UserDto;
import com.world.meet.w2m.exception.GenericException;

public interface UserService
{
	ResponseDto validateUser(String user,String password) throws GenericException;
}
