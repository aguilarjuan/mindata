package com.world.meet.w2m.service;

import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;

import java.util.List;

public interface SuperheroService
{
	SuperHeroDto create(SuperHeroDto superHeroDto) throws GenericException;
	SuperHeroDto update(SuperHeroDto superHeroDto) throws GenericException;
	List<SuperHeroDto> findAll() throws GenericException;
	void deleteById(Long id) throws GenericException;
	SuperHeroDto findById(Long id) throws GenericException;
	List<SuperHeroDto> findByPattern(String pattern) throws GenericException;
}
