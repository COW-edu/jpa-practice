package com.example.jpa.board.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BoardCreateRequest {

    @Column(name = "user_id")
    private Long id;

    private String title;
    private String content;

    public Board toEntity(Member writer) {
        return Board.builder()
                .writer(writer)
                .title(this.title)
                .content(this.content)
                .date(LocalDate.now())
                .build();
    }

}
