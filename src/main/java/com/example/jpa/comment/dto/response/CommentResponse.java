package com.example.jpa.comment.dto.response;

import com.example.jpa.comment.domain.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {

    private Long id;
    private String body;

    public CommentResponse(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getBody());
    }
}