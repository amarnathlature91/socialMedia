package com.smedia.service.implementation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smedia.dto.CommentsDTO;
import com.smedia.entity.Comments;
import com.smedia.entity.Post;
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
	@Override
	public CommentsDTO createComment(int postId,CommentsDTO cdto) {

		Post pst = pre.findById(postId).orElseThrow(null);
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

}
