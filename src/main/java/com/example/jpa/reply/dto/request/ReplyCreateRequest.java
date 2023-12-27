package com.example.jpa.reply.dto.request;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class ReplyCreateRequest {

    @Column(name = "board_id")
    private Long board_id;

    @Column(name = "member_id")
    private Long member_id;

    private String content;
}
