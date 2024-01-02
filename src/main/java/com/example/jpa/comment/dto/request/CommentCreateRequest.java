package com.example.jpa.comment.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.comment.domain.Comment;
import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private Long member;
    private Long board;
    private String body;

    public Comment toEntity(Member member, Board board) {
        return Comment.builder()
                .member(member)
                .board(board)
                .body(this.body)
                .build();
    }
}
