package com.smedia.dto;

public class JwtAuthResponseDTO {
	private String accessToken;
	private String tokenType="Bearer";
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	public JwtAuthResponseDTO(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
	
	public JwtAuthResponseDTO() {
		super();
	}

}
