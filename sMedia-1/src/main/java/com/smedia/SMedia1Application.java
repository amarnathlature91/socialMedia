package com.smedia;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SMedia1Application {

	public static void main(String[] args) {
		SpringApplication.run(SMedia1Application.class, args);
	}
	
	@Bean     
	public ModelMapper modelMapper() {
         return new ModelMapper();     }

}
