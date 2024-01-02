package com.example.jpa.reply.dto.response;

import com.example.jpa.reply.domain.Reply;
import lombok.Getter;

@Getter
public class ReplyResponse {

    private Long id;
    private String content;
    private String userName;
    private Long boardId;

    public ReplyResponse(Long id, String content, String userName, Long boardId) {
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.boardId = boardId;
    }

    public static ReplyResponse from(Reply reply) {
        return new ReplyResponse(reply.getId(), reply.getContent(), reply.getMember().getName(), reply.getBoard().getId());
    }
}
