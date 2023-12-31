package com.example.jpa.member.domain;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.post.domain.Post;
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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
