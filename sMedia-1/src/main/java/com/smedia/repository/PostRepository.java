package com.smedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smedia.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
