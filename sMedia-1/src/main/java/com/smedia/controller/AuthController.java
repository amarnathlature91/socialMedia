package com.smedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smedia.dto.LoginDTO;
import com.smedia.dto.RegisterDTO;
import com.smedia.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService auts;
	
	@PostMapping(value = {"/login","signin"})
	public ResponseEntity<String> login(@RequestBody LoginDTO lgdto){
		return new ResponseEntity<String>(auts.login(lgdto),HttpStatus.OK);
	}
	
	@PostMapping(value = {"/register","/signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDTO rdto){
		return new ResponseEntity<String>(auts.register(rdto),HttpStatus.CREATED);
	}
}
