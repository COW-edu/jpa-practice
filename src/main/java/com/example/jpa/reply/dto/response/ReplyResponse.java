package com.example.jpa.reply.dto.response;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReplyResponse {

    private Long id;
    private Board board;
    private Member member;

    private String content;
    private LocalDate date;

    public ReplyResponse(Long id, String content, LocalDate date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public static ReplyResponse from(Reply reply) {
        return new ReplyResponse(reply.getId(), reply.getContent(), reply.getDate());
    }

}
