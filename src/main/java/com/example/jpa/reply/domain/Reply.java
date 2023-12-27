package com.example.jpa.reply.domain;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.dto.request.ReplyCreateRequest;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
    private LocalDate date;

    public static Reply of(ReplyCreateRequest replyCreateRequest, Member targetMember, Board targetBoard) {
        return Reply.builder()
                .board(targetBoard)
                .member(targetMember)
                .content(replyCreateRequest.getContent())
                .date(LocalDate.now())
                .build();
    }

    public void update(ReplyUpdateRequest replyUpdateRequest) {
        this.content = replyUpdateRequest.getContent();
        this.date = LocalDate.now();
    }
}
