package com.example.jpa.comment.domain;

import com.example.jpa.comment.dto.request.CommentUpdateRequest;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member")
	private Post post;
	private String content;

	@Builder
	public Comment(Member member, Post post, String content) {
		this.member = member;
		this.post = post;
		this.content = content;
	}

	public void update(CommentUpdateRequest commentUpdateRequest) {
		this.content = commentUpdateRequest.getContent();
	}
}
