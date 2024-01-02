package com.example.jpa.comment.dto.request;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;

import lombok.Getter;

@Getter
public class CommentCreateRequest {

	private Long member;
	private Long post;
	private String content;

	public Comment toEntity(Member member, Post post) {
		return Comment.builder()
			.member(member)
			.post(post)
			.content(this.content)
			.build();
	}
}
