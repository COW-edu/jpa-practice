package com.example.jpa.post.dto.request;

import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;

import lombok.Getter;

@Getter
public class PostCreateRequest {

	private Long member;
	private String title;
	private String content;

	public Post toEntity(Member member) {
		return Post.builder()
			.member(member)
			.title(this.title)
			.content(this.content)
			.build();
	}
}
