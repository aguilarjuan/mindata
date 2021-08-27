package com.world.meet.w2m.controller;

import com.world.meet.w2m.annotation.ExecutionTimeMetric;
import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.service.SuperheroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/management")
public class SuperHeroController
{

	private final SuperheroService superheroService;

	@PostMapping(path = "/superHero", produces =  MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody SuperHeroDto superHeroDto){
		log.info("request create superhero with data {}",superHeroDto.toString());
		try
		{
			ResponseDto<SuperHeroDto> responseDto = superheroService.create(superHeroDto);
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			return GenericException.GenericExceptionResponse(e);
		}

	}

	@PutMapping(path = "/superHero", produces =  MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SuperHeroDto superHeroDto){
		log.info("request update superhero with data {}",superHeroDto.toString());
		try
		{
			ResponseDto<SuperHeroDto> responseDto  = superheroService.update(superHeroDto);
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			return GenericException.GenericExceptionResponse(e);
		}

	}

	@DeleteMapping(path = "/superHero/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		log.info("request delete superhero with ID {}", id.toString());
		try
		{
			ResponseDto<String> responseDto	= superheroService.deleteById(id);
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			return GenericException.GenericExceptionResponse(e);
		}
	}

	@GetMapping(path = "/superHero/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id){
		log.info("request find superhero by ID {}", id.toString());
		try
		{
			ResponseDto<SuperHeroDto> responseDto = superheroService.findById(id);
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (GenericException e){
			return GenericException.GenericExceptionResponse(e);
		}
	}

	@ExecutionTimeMetric
	@GetMapping(path = "/superHero/pattern/{pattern}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByPattern(@PathVariable String pattern){
		log.info("request find superhero by Pattern  {}", pattern);
		try
		{
			ResponseDto<List<SuperHeroDto>> responseDto = superheroService.findByPattern(pattern);
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (GenericException e){
			return GenericException.GenericExceptionResponse(e);
		}
	}

	@ExecutionTimeMetric
	@GetMapping(path = "/superHero", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll(){
		log.info("request findAll superhero");
		try
		{
			ResponseDto<List<SuperHeroDto>>  responseDto = superheroService.findAll();
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (GenericException e){
			return GenericException.GenericExceptionResponse(e);
		}
	}

}
