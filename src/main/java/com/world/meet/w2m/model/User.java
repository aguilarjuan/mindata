package com.world.meet.w2m.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User
{

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "USER_SEQ",  allocationSize = 1)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "token")
	private String token;

	public User()
	{
	}

	public User(Long id, String username, String password, String role, String token)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.token = token;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", role='"
				+ role + '\'' + ", token='" + token + '\'' + '}';
	}
}
