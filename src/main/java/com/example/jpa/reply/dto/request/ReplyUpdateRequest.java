package com.example.jpa.reply.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class ReplyUpdateRequest {

    @Column(name = "reply_id")
    private Long id;

    private String content;
}
