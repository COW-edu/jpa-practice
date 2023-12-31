package com.example.jpa.comment.domain;

import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    private String content;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    void update(String content) {
        this.content = content;
    }
}
