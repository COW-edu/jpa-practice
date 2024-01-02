package com.example.jpa.reply.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import lombok.Getter;

@Getter
public class ReplyCreateRequest {
    private Long id;
    private Member member;
    private Board board;
    private String content;

    public Reply toEntity() {
        return Reply.builder()
                .id(this.id)
                .member(this.member)
                .board(this.board)
                .content(this.content)
                .build();
    }
}
