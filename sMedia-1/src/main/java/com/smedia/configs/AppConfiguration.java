package com.smedia.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smedia.security.JwtAuthenticationFilter;

@Configuration
public class AppConfiguration {

	 @Bean
	 ModelMapper modelMapper() {
	return new ModelMapper();     } 
	 
	 @Bean
	 JwtAuthenticationFilter jwtAuthenticationFilter() {
		 return new JwtAuthenticationFilter();
	 }

}
