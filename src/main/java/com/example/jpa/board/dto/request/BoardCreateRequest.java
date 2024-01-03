package com.example.jpa.board.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class BoardCreateRequest {
    private String title;
    private String content;
    private Member member;

    public Board toEntity(Member member) {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .member(member)
                .build();
    }
}
