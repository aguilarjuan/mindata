package com.world.meet.w2m.controller;

import com.world.meet.w2m.dto.ErrorDto;
import com.world.meet.w2m.dto.ResponseDto;
import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/management")
public class SuperHeroController
{

	@Autowired
	private SuperheroService superheroService;

	@PostMapping(path = "/superHero", produces =  MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody SuperHeroDto superHeroDto){
		ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
		try
		{
			SuperHeroDto dto  = superheroService.create(superHeroDto);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());

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
		ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
		try
		{
			SuperHeroDto dto = superheroService.update(superHeroDto);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
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
		ResponseDto<String> responseDto =  new ResponseDto<>();
		try
		{
			superheroService.deleteById(id);
			responseDto.setData("borrado exitoso");
			responseDto.setStatusCode(HttpStatus.OK.toString());
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
		ResponseDto<SuperHeroDto> responseDto =  new ResponseDto<>();
		try
		{
			SuperHeroDto dto = superheroService.findById(id);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());

			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/superHero/pattern/{pattern}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByPatter(@PathVariable String pattern){
		ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
		try
		{
			List<SuperHeroDto> dto = superheroService.findByPattern(pattern);
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@GetMapping(path = "/superHero", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll(){

		ResponseDto<List<SuperHeroDto>> responseDto =  new ResponseDto<>();
		try
		{
			List<SuperHeroDto> dto = superheroService.findAll();
			responseDto.setData(dto);
			responseDto.setStatusCode(HttpStatus.OK.toString());

			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} catch (GenericException e){
			ErrorDto errorDto = new ErrorDto(e.getMessage(), e.getErrorCode());
			responseDto.setError(errorDto);
			responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
