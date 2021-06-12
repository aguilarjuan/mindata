package com.world.meet.w2m.mapper;

import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.model.SuperHero;
import org.springframework.stereotype.Component;

@Component
public class SuperHeroMapper {

	public SuperHero toEntity(SuperHeroDto superHeroDto){
		SuperHero entity = new SuperHero();
		entity.setName(superHeroDto.getName());
		return entity;
	}

	public SuperHeroDto toDto(SuperHero superHero){
		SuperHeroDto dto = new SuperHeroDto();
		dto.setId(superHero.getId());
		dto.setName(superHero.getName());
		return dto;
	}

	public SuperHero updateEntity(SuperHero superHero){
		SuperHero entityCurrent = new SuperHero();
		entityCurrent.setId(superHero.getId());
		entityCurrent.setName(superHero.getName());
		return entityCurrent;
	}

}
