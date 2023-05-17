package com.smedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smedia.dto.CommentsDTO;
import com.smedia.service.CommentsService;

@RestController
@RequestMapping("/api/posts")
public class CommentsController {

	@Autowired
	private CommentsService csr;
	
	@PostMapping("/{postId}/comments")
	public CommentsDTO create(@PathVariable int postId ,@RequestBody CommentsDTO cdto) {
		return csr.createComment(postId, cdto);
	}
	
	@GetMapping("/{postId}/comments")
	public List<CommentsDTO> getCommentsByPostId(@PathVariable int postId){
		return csr.getCommentsByPostId(postId);
	}
}
