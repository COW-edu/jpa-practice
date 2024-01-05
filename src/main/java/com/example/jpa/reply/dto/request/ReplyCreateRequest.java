package com.example.jpa.reply.dto.request;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReplyCreateRequest {

    @NotBlank
    private Long board_id;

    @NotBlank
    private Long member_id;

    @NotBlank
    private String content;

    public Reply toEntity(Member targetMember, Board targetBoard) {
        return Reply.builder()
                .member(targetMember)
                .board(targetBoard)
                .content(this.content)
                .date(LocalDate.now())
                .build();
    }
}
