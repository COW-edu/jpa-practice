package com.example.jpa.board.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;

public class BoardCreateRequest {
    private String title;
    private String content;
    private Member member;

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .member(this.member)
                .build();
    }
}
