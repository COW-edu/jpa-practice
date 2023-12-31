package com.example.jpa.comment.controller;

import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findOne(@PathVariable("id") Long id) {

        CommentResponse commentResponse = commentService.findOne(id);

        return ResponseEntity.ok(commentResponse);
    }
}
