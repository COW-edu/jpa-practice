package com.example.jpa.post.service;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.member.domain.Member;
import com.example.jpa.post.domain.Post;
import com.example.jpa.post.dto.request.PostCreateRequest;
import com.example.jpa.post.dto.request.PostUpdateRequest;
import com.example.jpa.post.dto.response.PostResponse;
import com.example.jpa.repository.CommentRepository;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void create(PostCreateRequest postCreateRequest) {
        Member member = memberRepository.findById(postCreateRequest.getMember()).get();
        postRepository.save(postCreateRequest.toEntity(member));
    }

    @Transactional(readOnly = true)
    public PostResponse findOne(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        List<Comment> comments = commentRepository.findAll().stream().toList();

        return PostResponse.from(post, post.getMember().getName(), comments);
    }

    @Transactional
    public void update(Long id, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}
