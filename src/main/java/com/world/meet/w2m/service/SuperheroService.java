package com.world.meet.w2m.service;

import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;

import java.util.List;

public interface SuperheroService
{
	ResponseDto<SuperHeroDto> create(SuperHeroDto superHeroDto) throws GenericException;
	ResponseDto<SuperHeroDto> update(SuperHeroDto superHeroDto) throws GenericException;
	ResponseDto<List<SuperHeroDto>> findAll() throws GenericException;
	ResponseDto<String> deleteById(Long id) throws GenericException;
	ResponseDto<SuperHeroDto> findById(Long id) throws GenericException;
	ResponseDto<List<SuperHeroDto>>  findByPattern(String pattern) throws GenericException;
}
