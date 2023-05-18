package com.smedia.service.implementation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.smedia.dto.CommentsDTO;
import com.smedia.entity.Comments;
import com.smedia.entity.Post;
import com.smedia.exception.ResourceNotFoundException;
import com.smedia.exception.sMediaException;
import com.smedia.repository.CommentsRepository;
import com.smedia.repository.PostRepository;
import com.smedia.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	private CommentsRepository cre;
	
	@Autowired
	private ModelMapper mdm;
	
	@Autowired
	private PostRepository pre;
	
	private CommentsDTO mapToDTO(Comments cm) {
		return mdm.map(cm, CommentsDTO.class);
	}
	
	private Comments mapToComments(CommentsDTO cdto) {
		return mdm.map(cdto, Comments.class);
	}
	
	private boolean checkPostComment(int postId,int CommentId) {
		Post post = pre.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
	    Comments comment = cre.findById(CommentId).orElseThrow(()-> new ResourceNotFoundException("Comment","CommentId",CommentId));
	    
	    if(post.getId()==comment.getPost().getId()) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public CommentsDTO createComment(int postId,CommentsDTO cdto) {

		Post pst = pre.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		Comments cm=mapToComments(cdto);
		cm.setPost(pst);
		return mapToDTO(cre.save(cm));
	}

	@Override
	public List<CommentsDTO> getCommentsByPostId(int postId) {
		Post pst=pre.findById(postId).orElseThrow();
		Set<Comments> cset = pst.getComments();
		return cset.stream().map((Comments)->mapToDTO(Comments)).collect(Collectors.toList());
	}

	@Override
	public CommentsDTO getCommentById(int postId, int commentId) {
		if(checkPostComment(postId, commentId)) {
			Comments cm = cre.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
			return mapToDTO(cm);
		}
		else {
			throw new sMediaException(HttpStatus.BAD_REQUEST,String.format("Post with postId=%s does not belong with Comment with commentId=%s",postId,commentId));
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public CommentsDTO updateComment(int postId, int commentId, CommentsDTO cdto) {
		if(checkPostComment(postId, commentId)) {
			Comments cm=cre.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","CommentId", commentId));
			cm.setBody(cdto.getBody());
			cm.setEmail(cdto.getEmail());
			cm.setName(cdto.getName());
			
			return mapToDTO(cre.save(cm));
		}
		else {
			throw new sMediaException(HttpStatus.BAD_REQUEST,String.format("Post with postId=%s does not belong with Comment with commentId=%s",postId,commentId));
		}
	}

	@Override
	public String deleteComment(int postId, int commentId) {
		if(checkPostComment(postId, commentId)) {
			cre.deleteById(commentId);
			return String.format("Comment with commentId=%s is deleted successfully", commentId);
		}
		else {
			throw new sMediaException(HttpStatus.BAD_REQUEST,String.format("Post with postId=%s does not belong with Comment with commentId=%s",postId,commentId));
		}
	}

}
