package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
