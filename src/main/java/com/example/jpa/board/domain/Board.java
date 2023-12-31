package com.example.jpa.board.domain;

import com.example.jpa.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

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
    public Board(Long id, String title, String content, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
