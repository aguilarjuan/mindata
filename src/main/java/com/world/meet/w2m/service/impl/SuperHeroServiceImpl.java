package com.world.meet.w2m.service.impl;

import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.service.SuperheroService;
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
import org.springframework.http.HttpStatus;
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
	public ResponseDto<SuperHeroDto>  create(SuperHeroDto superHeroDto) throws GenericException
	{
		try
		{
			ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
			SuperHeroDto dto = superHeroMapper.toDto(this.superHeroRepository.save(this.superHeroMapper.toEntity(superHeroDto)));
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return responseDto;

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
	ResponseDto<SuperHeroDto> update(SuperHeroDto superHeroDto) throws GenericException
	{
		try
		{
			SuperHero entityCurrent = this.superHeroRepository.findById(superHeroDto.getId()).orElseThrow(() ->
					new SuperHeroNotFoundException(SuperHeroNotFoundException.MESSAGE,SuperHeroNotFoundException.ERROR_CODE));
				    entityCurrent.setName(superHeroDto.getName());
			        this.superHeroRepository.save(entityCurrent);
			ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
			SuperHeroDto dto =  this.superHeroMapper.toDto(this.superHeroMapper.updateEntity(entityCurrent));
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return responseDto;

		} catch (SuperHeroNotFoundException e){
			throw e;
		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}

	@Override
	@Cacheable(value = "superherosList")
	public ResponseDto<List<SuperHeroDto>> findAll() throws GenericException
	{
		try
		{
			List<SuperHeroDto> dto = new ArrayList<>();
			List<SuperHero> entityList = this.superHeroRepository.findAll();
		for (SuperHero superHero : entityList ){
			dto.add(this.superHeroMapper.toDto(superHero));
		}
			ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return responseDto;

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
	public ResponseDto<String> deleteById(Long id) throws GenericException
	{
		try
		{
			this.superHeroRepository.deleteById(id);
			ResponseDto<String> responseDto =  new ResponseDto<>();
			responseDto.setData("borrado exitoso");
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return responseDto;
		} catch (Exception e){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}

	}

	@Override
	@Cacheable(value = "superheros", key = "#id")
	public ResponseDto<SuperHeroDto> findById(Long id) throws GenericException
	{
		try
		{
			SuperHero entity = this.superHeroRepository.findById(id).orElseThrow(() ->
					new SuperHeroNotFoundException(SuperHeroNotFoundException.MESSAGE,SuperHeroNotFoundException.ERROR_CODE));
			SuperHeroDto dto = this.superHeroMapper.toDto(entity);
			ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return responseDto;
		} catch (SuperHeroNotFoundException e){
			throw e;
		} catch (Exception ex){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}

	@Override
	@Caching(put = { @CachePut(value = "superherosPatternList", key = "#pattern") })
	public ResponseDto<List<SuperHeroDto>> findByPattern(String pattern) throws GenericException
	{
		try
		{
			List<SuperHeroDto> dto = new ArrayList<>();
			List<SuperHero> entityList = this.superHeroRepository.findByNameContaining(pattern);
			for (SuperHero superHero : entityList ){
				 if(superHero.getPattern(pattern)){
					 dto.add(this.superHeroMapper.toDto(superHero));
				 }
			}
			ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return responseDto;
		} catch (Exception ex){
			throw new ProviderDataBaseException(ProviderDataBaseException.MESSAGE,ProviderDataBaseException.ERROR_CODE);
		}
	}
}
