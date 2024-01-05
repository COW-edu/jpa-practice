package com.example.jpa.reply.dto.response;

import com.example.jpa.reply.domain.Reply;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReplyResponse {

    private Long id;
    private String boardTitle;
    private String memberName;

    private String content;
    private LocalDate date;

    public ReplyResponse(Long id, String boardTitle, String memberName, String content, LocalDate date) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.memberName = memberName;
        this.content = content;
        this.date = date;
    }

    public static ReplyResponse from(Reply reply) {
        return new ReplyResponse(reply.getId(), reply.getBoard().getTitle(), reply.getMember().getName(), reply.getContent(), reply.getDate());
    }

}
