package com.smedia.service;

import com.smedia.dto.ApiResponse;
import com.smedia.dto.PostDTO;

public interface PostService{
	
	public ApiResponse<PostDTO> getAllPosts(int pageNumber, int pageSize, String sortBy,String sortDir);
	
	public PostDTO createPost(PostDTO psd);
	
	public PostDTO getPostById(int postId);

	public PostDTO updatePost(int postId,PostDTO pd);
	
	public String DeletePost(int postId);
}
