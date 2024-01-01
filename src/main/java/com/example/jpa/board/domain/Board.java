package com.example.jpa.board.domain;

import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany
    private List<Reply> replyList = new ArrayList<Reply>();

    @Builder
    public Board(Long id, String title, String content, Member member, List<Reply> replyList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.replyList = replyList;
    }

    public void update(BoardUpdateRequest boardUpdateRequest) {
        this.title = boardUpdateRequest.getTitle();
        this.content = boardUpdateRequest.getContent();
        this.member = boardUpdateRequest.getMember();
    }
}
