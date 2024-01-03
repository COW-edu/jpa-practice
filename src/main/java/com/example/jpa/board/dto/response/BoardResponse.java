package com.example.jpa.board.dto.response;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private Long memberId;


    private String memberName;
    private List<Reply> replyList;

    public BoardResponse(Long id, String title, String content, Long memberId, String memberName, List<Reply> replyList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
        this.replyList = replyList;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getMember().getId(), board.getMember().getName(), board.getReplyList());
    }
}
