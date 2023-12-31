package com.example.jpa.post.controller;

import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.comment.service.CommentService;
import com.example.jpa.post.dto.request.PostCreateRequest;
import com.example.jpa.post.dto.request.PostUpdateRequest;
import com.example.jpa.post.dto.response.PostResponse;
import com.example.jpa.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostCreateRequest postCreateRequest) {

        postService.create(postCreateRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findOne(@PathVariable("id") Long id) {

        PostResponse postResponse = postService.findOne(id);

        return ResponseEntity.ok(postResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable("id") Long id, @RequestBody PostUpdateRequest postUpdateRequest) {

        postService.update(id, postUpdateRequest);

        return ResponseEntity.ok().build();
    }
}
