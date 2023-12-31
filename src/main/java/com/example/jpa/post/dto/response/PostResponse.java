package com.example.jpa.post.dto.response;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String name;
    private String content;
    private List<Comment> comments;

    public PostResponse(Long id, String title, String name, String content, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.content = content;
        this.comments = comments;
    }

    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static PostResponse from(Post post, String name, List<Comment> comments) {
        return new PostResponse(post.getId(), post.getTitle(), name, post.getContent(), comments);
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }
}
