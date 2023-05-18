package com.smedia.service.implementation;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.smedia.dto.ApiResponse;
import com.smedia.dto.PostDTO;
import com.smedia.entity.Post;
import com.smedia.exception.ResourceNotFoundException;
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
	public ApiResponse<PostDTO> getAllPosts(int pageNumber, int pageSize, String sortBy,String sortDir) {
		
		Sort sr=null;
		if(sortDir.equalsIgnoreCase("Desc")) {
			sr=Sort.by(sortBy).descending();
		}
		else {
			sr=Sort.by(sortBy).ascending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sr);
	    Page<Post> page = pre.findAll(pageable);
	    List<Post> plist=page.getContent();
	    List<PostDTO> pdlist=plist.stream().map((post)->mapToDTO(post)).collect(Collectors.toList());
	    
	    ApiResponse<PostDTO> ar=new ApiResponse<>();
	    ar.setContent(pdlist);
	    ar.setPageNumber(page.getNumber());
	    ar.setPageSize(page.getSize());
	    ar.setTotalPages(page.getTotalPages());
	    ar.setTotalElements(page.getTotalElements());
	    ar.setFirst(page.isFirst());
	    ar.setLast(page.isLast());
	    return ar;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public PostDTO createPost(PostDTO psd) {
		
		return mapToDTO(pre.save(mapToPost(psd)));
	}

	@Override
	public PostDTO getPostById(int postId) {
		Post pst=pre.findById(postId).orElseThrow(null);
		return mapToDTO(pst);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public PostDTO updatePost(int postId, PostDTO pd) {
		Post ps = pre.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id",postId));
		ps.setTitle(pd.getTitle());
		ps.setDescription(pd.getDescription());
		ps.setContent(pd.getContent());
		
		return mapToDTO(pre.save(ps));
	}

	@Override
	public String DeletePost(int postId) {
		Post pst = pre.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		pre.delete(pst); 
		return "Post with postId="+postId+"is deleted successfully";
		}

}
