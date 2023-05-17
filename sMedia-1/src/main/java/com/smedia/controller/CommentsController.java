package com.smedia.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.smedia.dto.CommentsDTO;
import com.smedia.service.CommentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class CommentsController {

	@Autowired
	private CommentsService csr;
	
	@PostMapping("/{postId}/comments")
	public ResponseEntity<CommentsDTO> create(@PathVariable int postId ,@Valid @RequestBody CommentsDTO cdto) {
		return new ResponseEntity<CommentsDTO>(csr.createComment(postId, cdto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{postId}/comments")
	public ResponseEntity<List<CommentsDTO>> getCommentsByPostId(@PathVariable int postId){
		return new ResponseEntity<>(csr.getCommentsByPostId(postId),HttpStatus.OK);
	}
	
	@GetMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<CommentsDTO> getCommentById(@PathVariable int postId,@PathVariable int commentId){
		return new ResponseEntity<CommentsDTO>(csr.getCommentById(postId, commentId),HttpStatus.OK);
	}
	
	@PutMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<CommentsDTO> updateById(@PathVariable int postId,@PathVariable int commentId,@Valid @RequestBody CommentsDTO cdto){
		return new ResponseEntity<CommentsDTO>(csr.updateComment(postId, commentId, cdto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteById(@PathVariable int postId,@PathVariable int commentId){
		return new ResponseEntity<String>(csr.deleteComment(postId, commentId),HttpStatus.OK);
	}
}
