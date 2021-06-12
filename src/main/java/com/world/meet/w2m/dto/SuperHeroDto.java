package com.world.meet.w2m.dto;

public class SuperHeroDto
{

	private Long id;
	private String name;

	public SuperHeroDto()
	{
	}

	public SuperHeroDto(Long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override public String toString()
	{
		return "SuperHeroDto{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
