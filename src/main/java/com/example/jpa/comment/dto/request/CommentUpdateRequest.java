package com.example.jpa.comment.dto.request;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;

import lombok.Getter;

@Getter
public class CommentUpdateRequest {

	private String content;

}
