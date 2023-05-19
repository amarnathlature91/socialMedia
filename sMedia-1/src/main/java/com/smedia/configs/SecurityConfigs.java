package com.smedia.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smedia.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableMethodSecurity
public class SecurityConfigs {

	@SuppressWarnings("unused")
	@Autowired
	private UserDetailsService usrd;
	
	@Autowired
	private JwtAuthenticationFilter authfilter;
	
	@Autowired
	private AuthenticationEntryPoint authentry;
	
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
	        .exceptionHandling(exception -> exception
	        		.authenticationEntryPoint(authentry))
	        .sessionManagement(session->session
	        		.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterAfter(authfilter, UsernamePasswordAuthenticationFilter.class)
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

//	return http.authorizeHttpRequests(auth -> auth
//            .requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
//            .requestMatchers("/api/auth/**").permitAll()
//            .anyRequest().authenticated())
//        .csrf(csrf -> csrf.disable())
//        .httpBasic(withDefaults())
//        .build();
	
//	 http.authorizeHttpRequests(auth -> auth
//	            .requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
//	            .requestMatchers("/api/auth/**").permitAll()
//	            .anyRequest().authenticated())
//	        .csrf(csrf -> csrf.disable())
//	        .exceptionHandling(exception -> exception
//	        		.authenticationEntryPoint(authentry))
//	        .sessionManagement(session->session
//	        		.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		 http.addFilterAfter(authfilter, UsernamePasswordAuthenticationFilter.class);
//		 return http.build();
}
