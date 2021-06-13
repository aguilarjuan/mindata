package com.world.meet.w2m.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private String username;
	private String password;
	private String role;
	private String token;

	public UserDto(String username, String password, String role, String token)
	{
		this.username = username;
		this.password = password;
		this.role = role;
		this.token = token;
	}

	public UserDto()
	{
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Override public String toString()
	{
		return "UserDto{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", role='" + role + '\''
				+ ", token='" + token + '\'' + '}';
	}
}
