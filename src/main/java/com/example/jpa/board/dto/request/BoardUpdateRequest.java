package com.example.jpa.board.dto.request;

import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class BoardUpdateRequest {
    private Long id;
    private String title;
    private String content;
    private Member member;
}
