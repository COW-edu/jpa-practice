package com.example.jpa.post.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;
import com.example.jpa.post.dto.request.PostCreateRequest;
import com.example.jpa.post.dto.request.PostUpdateRequest;
import com.example.jpa.post.dto.response.PostResponse;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public void create(PostCreateRequest postCreateRequest) {
		Member member = memberRepository.findById(postCreateRequest.getMember()).get();
		postRepository.save(postCreateRequest.toEntity(member));
	}

	@Transactional(readOnly = true)
	public PostResponse findOne(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));

		return PostResponse.from(post);
	}

	@Transactional(readOnly = true)
	public List<PostResponse> findAll() {
		List<Post> posts = postRepository.findAll();
		if (posts.isEmpty()) {
			throw new NoSuchElementException("게시물이 존재하지 않습니다.");
		}

		return posts.stream()
			.map(PostResponse::from)
			.collect(Collectors.toList());
	}

	public void update(Long id, PostUpdateRequest postUpdateRequest) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
		post.update(postUpdateRequest);
	}

	public void delete(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
		postRepository.delete(post);
	}
}
