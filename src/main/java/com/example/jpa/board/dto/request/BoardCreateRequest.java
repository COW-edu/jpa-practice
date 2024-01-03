package com.example.jpa.board.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class BoardCreateRequest {
    private Long member;
    private String title;
    private String body;

    public Board toEntity(Member member) {
        return Board.builder()
                .member(member)
                .title(this.title)
                .body(this.body)
                .build();
    }
}
