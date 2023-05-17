package com.smedia.dto;

import java.util.Set;

public class PostDTO {
	
	private int id;
	private String title;
	private String description;
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
