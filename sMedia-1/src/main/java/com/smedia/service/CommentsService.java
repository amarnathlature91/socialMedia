package com.smedia.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smedia.dto.CommentsDTO;

@Service
public interface CommentsService {

	public CommentsDTO createComment(int postId, CommentsDTO cdto);

	public List<CommentsDTO> getCommentsByPostId(int postId);

	public CommentsDTO getCommentById(int postId, int commentId);

	public CommentsDTO updateComment(int postId, int commentId, CommentsDTO cdto);

	public String deleteComment(int postId, int commentId);
}
