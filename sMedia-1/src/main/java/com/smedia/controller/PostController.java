package com.smedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smedia.dto.ApiResponse;
import com.smedia.dto.PostDTO;
import com.smedia.service.PostService;
import com.smedia.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService psre;

	@PostMapping("/createPost")
	public ResponseEntity<PostDTO> create(@Valid @RequestBody PostDTO psd) {
		return new ResponseEntity<PostDTO>(psre.createPost(psd),HttpStatus.CREATED);
	}

	@GetMapping("/getAllPosts")
	public ResponseEntity<ApiResponse<PostDTO>> getAllPosts(
			@RequestParam(name = "pgNum", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(name = "pgSz", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name = "srBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(name = "srDr", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

		ApiResponse<PostDTO> pg = psre.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<ApiResponse<PostDTO>>(pg, HttpStatus.OK);
	}

	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDTO> getById(@PathVariable int postId) {
		return new ResponseEntity<PostDTO>(psre.getPostById(postId),HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@PathVariable int postId,@Valid @RequestBody PostDTO psd){
		return new ResponseEntity<PostDTO>(psre.updatePost(postId, psd),HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable int postId){
		return new ResponseEntity<String>(psre.DeletePost(postId),HttpStatus.OK);
	}
}
