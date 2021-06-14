package com.world.meet.w2m.service.impl;

import com.world.meet.w2m.service.UserService;
import com.world.meet.w2m.dto.UserDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.exception.ProviderDataBaseException;
import com.world.meet.w2m.exception.SuperHeroNotFoundException;
import com.world.meet.w2m.exception.UserCredentialException;
import com.world.meet.w2m.mapper.UserMapper;
import com.world.meet.w2m.model.User;
import com.world.meet.w2m.repository.UserRepository;
import com.world.meet.w2m.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

	private final UserRepository userRepository;

	private final TokenUtils tokenUtils;

	private final UserMapper userMapper;

	@Override
	public UserDto validateUser(String username, String password) throws GenericException
	{
		try
		{
			User user = this.userRepository.findByUsernameAndPassword(username, password).orElseThrow(() ->
					new SuperHeroNotFoundException(UserCredentialException.MESSAGE,UserCredentialException.ERROR_CODE));
			String tokenUser = this.tokenUtils.getToken(user);
			user.setToken(tokenUser);
			this.userRepository.save(user);
			return this.userMapper.toDto(user);
		} catch (SuperHeroNotFoundException e){
			throw e;
		} catch (Exception ex){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}
}
