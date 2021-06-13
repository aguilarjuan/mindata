package com.world.meet.w2m.mapper;

import com.world.meet.w2m.dto.UserDto;
import com.world.meet.w2m.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{
	public UserDto toDto(User user){
		UserDto dto = new UserDto();
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setToken(user.getToken());
        return dto;
	}
}
