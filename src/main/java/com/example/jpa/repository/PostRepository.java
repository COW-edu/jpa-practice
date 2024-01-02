package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
