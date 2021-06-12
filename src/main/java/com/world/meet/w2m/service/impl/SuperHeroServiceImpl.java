package com.world.meet.w2m.service.impl;

import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.exception.ProviderDataBaseException;
import com.world.meet.w2m.exception.SuperHeroNotFoundException;
import com.world.meet.w2m.mapper.SuperHeroMapper;
import com.world.meet.w2m.model.SuperHero;
import com.world.meet.w2m.repository.SuperHeroRepository;
import com.world.meet.w2m.service.SuperheroService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperHeroServiceImpl implements SuperheroService
{

	private final SuperHeroRepository superHeroRepository;
    private final SuperHeroMapper superHeroMapper;

	@Override
	public SuperHeroDto create(SuperHeroDto superHeroDto) throws GenericException
	{
		try
		{
			return superHeroMapper.toDto(this.superHeroRepository.save(this.superHeroMapper.toEntity(superHeroDto)));
		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}

	}

	@Override
	public
	SuperHeroDto update(SuperHeroDto superHeroDto) throws GenericException
	{
		try
		{
			SuperHero entityCurrent = this.superHeroRepository.findById(superHeroDto.getId()).orElseThrow(() ->
					new SuperHeroNotFoundException(SuperHeroNotFoundException.MESSAGE,SuperHeroNotFoundException.ERROR_CODE));
			return this.superHeroMapper.toDto(this.superHeroMapper.updateEntity(entityCurrent));
		} catch (SuperHeroNotFoundException e){
			throw e;
		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}

	@Override
	public List<SuperHeroDto> findAll() throws GenericException
	{
		try
		{
			List<SuperHeroDto> response = new ArrayList<>();
			List<SuperHero> entityList = this.superHeroRepository.findAll();
		for (SuperHero superHero : entityList ){
			response.add(this.superHeroMapper.toDto(superHero));
		}
			return response;

		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}

	}

	@Override
	public void deleteById(Long id) throws GenericException
	{
		try
		{
			this.superHeroRepository.deleteById(id);
		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}

	}

	@Override
	public SuperHeroDto findById(Long id) throws GenericException
	{
		try
		{
			SuperHero entity = this.superHeroRepository.findById(id).orElseThrow(() ->
					new SuperHeroNotFoundException(SuperHeroNotFoundException.MESSAGE,SuperHeroNotFoundException.ERROR_CODE));
			return this.superHeroMapper.toDto(entity);
		} catch (SuperHeroNotFoundException e){
			throw e;
		} catch (Exception ex){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}

	@Override
	public List<SuperHeroDto> findByPattern(String pattern) throws GenericException
	{
		try
		{
			List<SuperHeroDto> response = new ArrayList<>();
			List<SuperHero> entityList = this.superHeroRepository.findAll();
			for (SuperHero superHero : entityList ){
				 if(superHero.getPattern(pattern)){
					 response.add(this.superHeroMapper.toDto(superHero));
				 }
			}
			return response;
		} catch (Exception ex){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}
}
