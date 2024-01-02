package com.example.jpa.reply.domain;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public Reply(Long id, Member member, Board board, String content) {
        this.id = id;
        this.member = member;
        this.board = board;
        this.content = content;
    }

    public void update(ReplyUpdateRequest replyUpdateRequest) {
        this.id = replyUpdateRequest.getId();
        this.member = replyUpdateRequest.getMember();
        this.board = replyUpdateRequest.getBoard();
        this.content = replyUpdateRequest.getContent();
    }
}
