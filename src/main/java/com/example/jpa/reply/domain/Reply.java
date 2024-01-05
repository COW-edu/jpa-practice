package com.example.jpa.reply.domain;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.dto.request.ReplyCreateRequest;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Reply(Member member, Board board, String content, LocalDate date) {
        this.board = board;
        this.member = member;
        this.content = content;
        this.date = date;

    }

    public void update(ReplyUpdateRequest replyUpdateRequest) {
        this.content = replyUpdateRequest.getContent();
        this.date = LocalDate.now();
    }
}
