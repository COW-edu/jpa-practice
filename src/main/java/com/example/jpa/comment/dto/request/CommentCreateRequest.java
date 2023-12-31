package com.example.jpa.comment.dto.request;

import com.example.jpa.comment.domain.Comment;
import lombok.Getter;

@Getter
public class CommentCreateRequest {

    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(this.content)
                .build();
    }
}
