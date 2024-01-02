package com.example.jpa.comment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.request.CommentUpdateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;
import com.example.jpa.repository.CommentRepository;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public void create(CommentCreateRequest commentCreateRequest) {
		Member member = memberRepository.findById(commentCreateRequest.getMember()).get();
		Post post = postRepository.findById(commentCreateRequest.getMember()).get();
		commentRepository.save(commentCreateRequest.toEntity(member,post));
	}

	@Transactional(readOnly = true)
	public CommentResponse findOne(Long id) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));

		return CommentResponse.from(comment);
	}

	@Transactional(readOnly = true)
	public List<CommentResponse> findAll() {
		List<Comment> comments = commentRepository.findAll();
		if (comments.isEmpty()) {
			throw new NoSuchElementException("댓글이 존재하지 않습니다.");
		}

		return comments.stream()
			.map(CommentResponse::from)
			.collect(Collectors.toList());
	}

	public void update(Long id, CommentUpdateRequest commentUpdateRequest) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
		comment.update(commentUpdateRequest);
	}

	public void delete(Long id) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
		commentRepository.delete(comment);
	}
}
