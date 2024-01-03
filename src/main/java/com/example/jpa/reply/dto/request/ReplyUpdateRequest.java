package com.example.jpa.reply.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class ReplyUpdateRequest {
    private Long id;
    private Member member;
    private Board board;
    private String content;
}
