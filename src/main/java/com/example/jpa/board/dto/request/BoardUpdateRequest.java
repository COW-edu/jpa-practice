package com.example.jpa.board.dto.request;


import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class BoardUpdateRequest {

    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
}
