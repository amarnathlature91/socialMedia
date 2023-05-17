package com.smedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smedia.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
