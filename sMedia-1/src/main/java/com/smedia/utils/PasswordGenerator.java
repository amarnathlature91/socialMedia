package com.smedia.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {

	public static void main(String[]args) {
		PasswordEncoder pencd=new BCryptPasswordEncoder();
		
		System.out.println(pencd.encode("Amar"));
		System.out.println(pencd.encode("Admin"));

		
	}
}
