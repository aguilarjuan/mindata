package com.world.meet.w2m.impl;

import com.world.meet.w2m.SuperheroService;
import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.exception.ProviderDataBaseException;
import com.world.meet.w2m.exception.SuperHeroNotFoundException;
import com.world.meet.w2m.mapper.SuperHeroMapper;
import com.world.meet.w2m.model.SuperHero;
import com.world.meet.w2m.repository.SuperHeroRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
	@Caching(evict = {
			@CacheEvict(value = "superherosList", allEntries = true),
			@CacheEvict(value = "superherosPatternList", allEntries = true) })
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
	@Caching(evict = {
			@CacheEvict(value = "superherosList", allEntries = true),
			@CacheEvict(value = "superherosPatternList", allEntries = true) },
			put = {
			@CachePut(value = "superheros", key = "#superHeroDto.getId()") })
	public
	SuperHeroDto update(SuperHeroDto superHeroDto) throws GenericException
	{
		try
		{
			SuperHero entityCurrent = this.superHeroRepository.findById(superHeroDto.getId()).orElseThrow(() ->
					new SuperHeroNotFoundException(SuperHeroNotFoundException.MESSAGE,SuperHeroNotFoundException.ERROR_CODE));
				    entityCurrent.setName(superHeroDto.getName());
			        this.superHeroRepository.save(entityCurrent);
			return this.superHeroMapper.toDto(this.superHeroMapper.updateEntity(entityCurrent));
		} catch (SuperHeroNotFoundException e){
			throw e;
		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}

	@Override
	@Cacheable(value = "superherosList")
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
	@CacheEvict(value = "superheros", allEntries = true)
	@Caching(evict = {
			@CacheEvict(value = "superherosList", allEntries = true),
			@CacheEvict(value = "superheros", allEntries = true) ,
			@CacheEvict(value = "superherosPatternList", allEntries = true)})
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
	@Cacheable(value = "superheros", key = "#id")
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
	@Caching(put = { @CachePut(value = "superherosPatternList", key = "#pattern") })
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
