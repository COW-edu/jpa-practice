package com.example.jpa.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardUpdateRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
