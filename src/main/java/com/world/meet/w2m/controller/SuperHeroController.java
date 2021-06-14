package com.world.meet.w2m.controller;

import com.world.meet.w2m.annotation.ExecutionTimeMetric;
import com.world.meet.w2m.dto.ErrorDto;
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
		ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
		try
		{
			SuperHeroDto dto  = superheroService.create(superHeroDto);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/superHero", produces =  MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SuperHeroDto superHeroDto){
		log.info("request update superhero with data {}",superHeroDto.toString());
		ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
		try
		{
			SuperHeroDto dto = superheroService.update(superHeroDto);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(path = "/superHero/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		log.info("request delete superhero with ID {}", id.toString());
		ResponseDto<String> responseDto =  new ResponseDto<>();
		try
		{
			superheroService.deleteById(id);
			responseDto.setData("borrado exitoso");
			responseDto.setStatusCode(HttpStatus.OK.toString());
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/superHero/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id){
		log.info("request find superhero by ID {}", id.toString());
		ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
		try
		{
			SuperHeroDto dto = superheroService.findById(id);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ExecutionTimeMetric
	@GetMapping(path = "/superHero/pattern/{pattern}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByPattern(@PathVariable String pattern){
		log.info("request find superhero by Pattern  {}", pattern);
		ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
		try
		{
			List<SuperHeroDto> dto = superheroService.findByPattern(pattern);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@ExecutionTimeMetric
	@GetMapping(path = "/superHero", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll(){
		log.info("request findAll superhero");
		ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
		try
		{
			List<SuperHeroDto> dto = superheroService.findAll();
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			log.info("response data {}", responseDto.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
