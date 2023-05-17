package com.smedia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smedia.dto.CommentsDTO;

@Service
public interface CommentsService {

	public CommentsDTO createComment(int postId,CommentsDTO cdto);
	
	public List<CommentsDTO> getCommentsByPostId(int postId);
}
