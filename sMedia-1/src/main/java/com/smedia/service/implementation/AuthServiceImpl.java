package com.smedia.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smedia.dto.LoginDTO;
import com.smedia.dto.RegisterDTO;
import com.smedia.entity.Role;
import com.smedia.entity.Users;
import com.smedia.exception.sMediaException;
import com.smedia.repository.RoleRepository;
import com.smedia.repository.UserRepository;
import com.smedia.security.JwtTokenProvider;
import com.smedia.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository usrp;
	
	@Autowired
	private RoleRepository rrp;
	
	@Autowired
	private AuthenticationManager autmn;
	
	@Autowired
	private PasswordEncoder penco;
	
	@Autowired
	private JwtTokenProvider jwtpr;
	
	@Override
	public String login(LoginDTO loginDto) {
		Authentication authenticate = autmn.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword() ));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		String token = jwtpr.generateToken(authenticate);
		
		return token;
	}

	@Override
	public String register(RegisterDTO redto) {
		if (usrp.existsByUserName(redto.getUserName())) {
			throw new sMediaException(HttpStatus.BAD_REQUEST,"Username alredy exists");
		}
		if (usrp.existsByEmail(redto.getEmail())) {
			throw new sMediaException(HttpStatus.BAD_REQUEST,"Email alredy exists");
		}
		Users usr=new Users();
		usr.setName(redto.getName());
		usr.setUserName(redto.getUserName());
		usr.setEmail(redto.getEmail());
		usr.setPassword(penco.encode(redto.getPassword()));
		
		Set<Role> roles=new HashSet<>();
		Role role=rrp.findByName("ROLE_USER").get();
		roles.add(role);
		usr.setRole(roles);
		
		usrp.save(usr);
		return String.format("User with userName=%s is registerd successfully", redto.getUserName());
		}

}
