package com.example.jpa.board.dto.response;

import com.example.jpa.board.domain.Board;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponse {
    private Long id;
    private String title;
    private String name;
    private String body;
    private List<String> comments;

    public BoardResponse(Long id, String title, String name, String body, List<String> comments) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.body = body;
        this.comments = comments;
    }

    public static BoardResponse from(Board board, String name, List<String> comments) {
        return new BoardResponse(board.getId(), board.getTitle(), name, board.getBody(), comments);
    }
}
