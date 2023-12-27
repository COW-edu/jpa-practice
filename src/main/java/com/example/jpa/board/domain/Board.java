package com.example.jpa.board.domain;

import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    private String title;
    private String content;
    private LocalDate date;

    public static Board of(BoardCreateRequest boardCreateRequest, Member member) {
        return Board.builder()
                .member(member)
                .title(boardCreateRequest.getTitle())
                .content(boardCreateRequest.getContent())
                .date(LocalDate.now())
                .build();
    }

    public void update(BoardUpdateRequest boardUpdateRequest) {
        this.title = boardUpdateRequest.getTitle();
        this.content = boardUpdateRequest.getContent();
        this.date = LocalDate.now();
    }
}
