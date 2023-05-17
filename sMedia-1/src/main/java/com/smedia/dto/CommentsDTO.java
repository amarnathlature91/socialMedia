package com.smedia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentsDTO {

	private int id;
	@NotEmpty(message = "name should not be null")
    private String name;
	@NotNull(message = "name should not be null")
	@Email(message = "Email must be in proper format")
    private String email;
	@NotEmpty(message = "comment body must not be empty")
	@Size(min=10,message = "comment body must contain minimum 10 characters")
    private String body;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public CommentsDTO() {
		super();
	}
	public CommentsDTO(int id, String name, String email, String body) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}
    
    
}
