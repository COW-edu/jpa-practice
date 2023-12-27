package com.example.jpa.board.dto.response;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BoardResponse {

    private Long id;
    private Member member;

    private String title;
    private String content;
    private LocalDate date;

    public BoardResponse(Long id, String title, String content, LocalDate date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getDate());
    }
}
