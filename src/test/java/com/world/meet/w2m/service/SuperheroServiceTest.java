package com.world.meet.w2m.service;

import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.exception.GenericException;
import com.world.meet.w2m.exception.SuperHeroNotFoundException;
import com.world.meet.w2m.mapper.SuperHeroMapper;
import com.world.meet.w2m.model.SuperHero;
import com.world.meet.w2m.repository.SuperHeroRepository;
import com.world.meet.w2m.service.impl.SuperHeroServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
class SuperheroServiceTest
{

	private SuperHeroRepository superHeroRepository;
	private SuperHeroMapper superHeroMapper;
	private SuperheroService superheroService;

	@BeforeEach
	public void setup(){
    superHeroRepository = mock(SuperHeroRepository.class);
    superHeroMapper = new SuperHeroMapper();
    superheroService =  new SuperHeroServiceImpl(superHeroRepository,superHeroMapper);

        SuperHero superman =  SuperHero.builder().id(1L).name("superman").build();
		Mockito.when(superHeroRepository.findById(1L))
				.thenReturn(Optional.of(superman));

		SuperHero hulk =  SuperHero.builder().id(3L).name("hulk").build();
		Mockito.when(superHeroRepository.findById(3L))
				.thenReturn(Optional.of(hulk));

		Mockito.when(superHeroRepository.findById(12L))
				.thenReturn(Optional.empty());

		SuperHero batman = SuperHero.builder().name("batman").build();
		Mockito.when(superHeroRepository.save(batman))
				.thenReturn(SuperHero.builder().id(2L).name("batman").build());

		List<SuperHero> superHeroList = new ArrayList<>();

		superHeroList.add(superman);
		superHeroList.add(batman);
		superHeroList.add(hulk);

		Mockito.when(superHeroRepository.findAll())
				.thenReturn(superHeroList);
	}

	@Test
	public void when_findById_Is_Called_With_Correct_id_Then_equal_SuperHero(){
		try{
			SuperHeroDto superHeroDto = superheroService.findById(1L);
			Assertions.assertThat(superHeroDto.getName()).isEqualTo("superman");
		} catch (GenericException e){
			Assertions.fail("Exception thrown");
		}
	}

	@Test
	public void when_findById_Is_Called_With_Incorrect_id_Then_Expected_SuperHeroNotFoundException(){
		Throwable exception	= assertThrows(SuperHeroNotFoundException.class, () -> {
			superheroService.findById(12L);
		});
		assertEquals(exception.getMessage(), "ERROR NOT FOUND ID");
	}

	@Test
	public void when_update_Is_Called_With_Incorrect_id_Then_Expected_SuperHeroNotFoundException(){
		SuperHeroDto dto = new SuperHeroDto(12L,"hulk");
		Throwable exception	= assertThrows(SuperHeroNotFoundException.class, () -> {
			superheroService.update(dto);
		});
		assertEquals(exception.getMessage(), "ERROR NOT FOUND ID");
	}

	@Test
	public void when_update_Is_Called_With_Correct_id_Then_Expected_equal_SuperHero(){
		try{
			SuperHeroDto dto = new SuperHeroDto(3L,"hulk");;
			SuperHeroDto superHeroDto = superheroService.update(dto);
			Assertions.assertThat(superHeroDto.getName()).isEqualTo("hulk");
		} catch (GenericException e){
			Assertions.fail("Exception thrown");
		}
	}

	@Test
	public void when_create_Is_Called_With_Corrects_parameters_Then_equal_SuperHero(){
		try{
			SuperHeroDto dto = new SuperHeroDto();
			dto.setName("batman");
			SuperHeroDto superHeroDto = superheroService.create(dto);
			Assertions.assertThat(superHeroDto.getName()).isEqualTo("batman");
		} catch (GenericException e){
			Assertions.fail("Exception thrown");
		}
	}

	@Test
	public void when_findAll_Is_Called_With_Corrects_parameters_Then_successful(){
		try{
		List<SuperHeroDto> superHeroList = superheroService.findAll();
			Assertions.assertThat(String.valueOf(superHeroList.size())).isEqualTo("3");
		} catch (GenericException e){
			Assertions.fail("Exception thrown");
		}
	}

	@Test
	public void when_findAll_By_Pattern_Is_Called_With_Corrects_Parameters_Then_Successful(){
		try{
			List<SuperHeroDto> superHeroList = superheroService.findByPattern("man");
			Assertions.assertThat(String.valueOf(superHeroList.size())).isEqualTo("2");
		} catch (GenericException e){
			Assertions.fail("Exception thrown");
		}
	}

}