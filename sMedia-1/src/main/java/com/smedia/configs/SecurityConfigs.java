package com.smedia.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfigs {

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		return http.authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/posts/**").permitAll()
	            .anyRequest().authenticated())
	        .csrf(csrf -> csrf.disable())
	        .httpBasic(withDefaults())
	        .build();
	}
	
	@Bean
	UserDetailsService userdetailsDervice() {
		
		UserDetails Amar = User.builder().username("AMAR").password(passwordEncoder().encode("AMAR")).roles("USER").build();
		UserDetails Admin = User.builder().username("ADMIN").password(passwordEncoder().encode("ADMIN")).roles("ADMIN").build();

		return new InMemoryUserDetailsManager(Amar,Admin);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
