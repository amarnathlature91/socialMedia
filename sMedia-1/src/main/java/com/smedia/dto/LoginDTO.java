package com.smedia.dto;

public class LoginDTO {
	
	private String userNameOrEmail;
	private String Password;
	
	
	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}
	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	public LoginDTO(String userNameOrEmail, String password) {
		super();
		this.userNameOrEmail = userNameOrEmail;
		Password = password;
	}
	
	
	public LoginDTO() {
		super();
	}
	
	

}
