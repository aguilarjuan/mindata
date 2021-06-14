package com.world.meet.w2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.world.meet.w2m")
@SpringBootApplication
@EnableCaching
public class MindataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindataApplication.class, args);
	}

}
