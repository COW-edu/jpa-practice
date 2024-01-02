package com.example.jpa.post.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.dto.request.PostUpdateRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member")
	private Member member;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	private String title;
	private String content;

	@Builder
	public Post(Member member, String title, String content, List<Comment> comments) {
		this.member = member;
		this.title = title;
		this.content = content;
		this.comments = comments;
	}

	public void update(PostUpdateRequest postUpdateRequest) {
		this.title = postUpdateRequest.getTitle();
		this.content = postUpdateRequest.getContent();
	}
}
