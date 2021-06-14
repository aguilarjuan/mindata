package com.world.meet.w2m;

import com.world.meet.w2m.dto.UserDto;
import com.world.meet.w2m.exception.GenericException;

public interface UserService
{
	UserDto validateUser(String user,String password) throws GenericException;
}
