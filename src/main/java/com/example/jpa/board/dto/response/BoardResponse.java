package com.example.jpa.board.dto.response;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private Long memberId;

    public BoardResponse(Long id, String title, String content, Long memberId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getId());
    }
}
