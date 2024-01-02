package com.example.jpa.post.dto.response;

import com.example.jpa.post.domain.Post;

import lombok.Getter;

@Getter
public class PostResponse {
	private Long id;
	private String name;
	private String title;
	private String content;

	public PostResponse(Long id, String name, String title, String content) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
	}

	public static PostResponse from(Post post) {
		return new PostResponse(post.getId(), post.getMember().getName(), post.getTitle(), post.getContent());
	}
}
