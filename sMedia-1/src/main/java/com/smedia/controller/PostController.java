package com.smedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smedia.dto.PostDTO;
import com.smedia.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService psre;
	
	@PostMapping("/createPost")
	public PostDTO create(@RequestBody PostDTO psd) {
		return psre.createPost(psd);
	}
	
	@GetMapping("/getAllPosts")
	public List<PostDTO> getAllPosts(){
		return psre.getAllPosts();
	}
	
	@GetMapping("/getPostById/{postId}")
	public PostDTO getById(@PathVariable int postId) {
		return psre.getPostById(postId);
	}
}
