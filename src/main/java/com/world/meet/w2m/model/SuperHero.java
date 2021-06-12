package com.world.meet.w2m.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "super_hero")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class SuperHero {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "superHero_generator")
	@SequenceGenerator(name="superHero_generator", sequenceName = "SUPER_HERO_SEQ",  allocationSize = 1)
	private Long id;
	@Column(name = "name")
	private String name;

	public boolean getPattern(String pattern) {
	 return this.name.contains(pattern);
	}
}
