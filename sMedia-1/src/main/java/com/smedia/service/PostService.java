package com.smedia.service;

import java.util.List;
import com.smedia.dto.PostDTO;


public interface PostService{
	
	public List<PostDTO> getAllPosts();
	
	public PostDTO createPost(PostDTO psd);
	
	public PostDTO getPostById(int postId);
}
