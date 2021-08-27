package com.world.meet.w2m.repository;

import com.world.meet.w2m.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository  extends JpaRepository<SuperHero, Long> {
	List<SuperHero> findByNameContaining(String pattern);
}
