package com.smedia.service;

import com.smedia.dto.LoginDTO;
import com.smedia.dto.RegisterDTO;

public interface AuthService {
	String login(LoginDTO loginDto);
	
	String register(RegisterDTO redto);
}
