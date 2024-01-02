package com.example.jpa.post.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.post.dto.request.PostCreateRequest;
import com.example.jpa.post.dto.request.PostUpdateRequest;
import com.example.jpa.post.dto.response.PostResponse;
import com.example.jpa.post.service.PostService;

import lombok.RequiredArgsConstructor;

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

	@GetMapping
	public ResponseEntity<List<PostResponse>> findAll() {

		List<PostResponse> posts = postService.findAll();

		return ResponseEntity.ok(posts);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PostResponse> update(@PathVariable("id") Long id, @RequestBody PostUpdateRequest postUpdateRequest) {

		postService.update(id, postUpdateRequest);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

		postService.delete(id);

		return ResponseEntity.ok().build();
	}
}
