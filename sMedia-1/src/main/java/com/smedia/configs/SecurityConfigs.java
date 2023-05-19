package com.smedia.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableMethodSecurity
public class SecurityConfigs {

	@SuppressWarnings("unused")
	@Autowired
	private UserDetailsService usrd;
	
	@Bean
	AuthenticationManager authenticationMager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		return http.authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
	            .requestMatchers("/api/auth/**").permitAll()
	            .anyRequest().authenticated())
	        .csrf(csrf -> csrf.disable())
	        .httpBasic(withDefaults())
	        .build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	UserDetailsService userdetailsDervice() {
//		
//		UserDetails Amar = User.builder().username("AMAR").password(passwordEncoder().encode("AMAR")).roles("USER").build();
//		UserDetails Admin = User.builder().username("ADMIN").password(passwordEncoder().encode("ADMIN")).roles("ADMIN").build();
//
//		return new InMemoryUserDetailsManager(Amar,Admin);
//	}
	
}
