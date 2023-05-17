package com.smedia.service.implementation;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smedia.dto.PostDTO;
import com.smedia.entity.Post;
import com.smedia.repository.PostRepository;
import com.smedia.service.PostService;
@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository pre;

	@Autowired
	ModelMapper mdm;
	private PostDTO mapToDTO(Post pst) {
		return mdm.map(pst, PostDTO.class);
	}
	
	private Post mapToPost(PostDTO psd) {
		return mdm.map(psd, Post.class);
	}
	
	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> plist=pre.findAll();
		return plist.stream().map((Post)->mapToDTO(Post)).collect(Collectors.toList());
	}

	@Override
	public PostDTO createPost(PostDTO psd) {
		
		return mapToDTO(pre.save(mapToPost(psd)));
	}

	@Override
	public PostDTO getPostById(int postId) {
		Post pst=pre.findById(postId).orElseThrow(null);
		return mapToDTO(pst);
	}

}
