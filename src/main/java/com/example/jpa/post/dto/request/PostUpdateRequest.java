package com.example.jpa.post.dto.request;

import com.example.jpa.post.domain.Post;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
