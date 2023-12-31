package com.example.jpa.comment.service;

import com.example.jpa.comment.domain.Comment;
import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void create(CommentCreateRequest commentCreateRequest) {
        commentRepository.save(commentCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public CommentResponse findOne(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));

        return CommentResponse.from(comment);
    }
}
