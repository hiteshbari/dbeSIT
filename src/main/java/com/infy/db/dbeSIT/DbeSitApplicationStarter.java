package com.infy.db.dbeSIT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DbeSitApplicationStarter  implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(DbeSitApplicationStarter.class, args);
	}
	
	/*
	 * To serve request from server available at a different origin Can be done at
	 * controller & method level as well
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**").allowedMethods("GET", "POST");
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

}
