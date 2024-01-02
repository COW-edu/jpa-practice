package com.example.jpa.board.dto.request;

import lombok.Getter;

@Getter
public class BoardUpdateRequest {
    private String title;
    private String body;
}
