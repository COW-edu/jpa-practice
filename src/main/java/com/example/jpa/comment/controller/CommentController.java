package com.example.jpa.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.request.CommentUpdateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CommentCreateRequest commentCreateRequest) {

		commentService.create(commentCreateRequest);

		return ResponseEntity.ok().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<CommentResponse> findOne(@PathVariable("id") Long id) {

		CommentResponse commentResponse = commentService.findOne(id);

		return ResponseEntity.ok(commentResponse);
	}

	@GetMapping
	public ResponseEntity<List<CommentResponse>> findAll() {

		List<CommentResponse> comments = commentService.findAll();

		return ResponseEntity.ok(comments);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CommentResponse> update(@PathVariable("id") Long id, @RequestBody CommentUpdateRequest commentUpdateRequest) {

		commentService.update(id, commentUpdateRequest);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

		commentService.delete(id);

		return ResponseEntity.ok().build();
	}

}
