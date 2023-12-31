package com.example.jpa.post.controller;

import com.example.jpa.post.dto.request.PostCreateRequest;
import com.example.jpa.post.dto.request.PostUpdateRequest;
import com.example.jpa.post.dto.response.PostResponse;
import com.example.jpa.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> delete(@PathVariable("id") Long id) {

        postService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List> findAll() {

        List<PostResponse> postResponses = postService.findAll();

        return ResponseEntity.ok(postResponses);
    }
}
