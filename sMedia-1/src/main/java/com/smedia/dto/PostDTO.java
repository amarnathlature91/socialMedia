package com.smedia.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDTO {
	
	private int id;
	
	@NotEmpty(message = "post title should not be empty")
	@Size(min = 5,message="Post title should be at least 5 characters")
	private String title;
	@NotEmpty(message = "post description should not be empty")
	@Size(min = 5,message="Post description should be at least 5 characters")
	private String description;
	@NotEmpty(message = "post content should not be empty")
	private String content;
	
	private Set<CommentsDTO> comments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<CommentsDTO> getComments() {
		return comments;
	}
	public void setComments(Set<CommentsDTO> comments) {
		this.comments = comments;
	}
	
	public PostDTO() {
		super();
	}
	public PostDTO(int id, String title, String description, String content, Set<CommentsDTO> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
	}
	
	
}
