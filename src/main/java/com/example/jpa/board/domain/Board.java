package com.example.jpa.board.domain;

import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    // @Builder - 생성자를 통해 반환하기 때문에 필요 X
    // @Setter - update 메서드를 통해 수정하기 때문에 필요 X

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    private String title;
    private String content;
    private LocalDate date;

    @Builder
    public Board(Member writer, String title, String content, LocalDate date) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void update(BoardUpdateRequest boardUpdateRequest) {
        this.title = boardUpdateRequest.getTitle();
        this.content = boardUpdateRequest.getContent();
        this.date = LocalDate.now();
    }
}
