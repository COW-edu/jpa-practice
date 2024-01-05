package com.example.jpa.board.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BoardCreateRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
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
