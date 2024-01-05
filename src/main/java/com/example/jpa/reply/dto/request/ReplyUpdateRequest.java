package com.example.jpa.reply.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReplyUpdateRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String content;
}
