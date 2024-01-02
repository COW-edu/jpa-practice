package com.example.jpa.comment.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.repository.BoardRepository;
import com.example.jpa.comment.domain.Comment;
import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.request.CommentUpdateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.comment.repository.CommentRepository;
import com.example.jpa.member.domain.Member;
import com.example.jpa.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(CommentCreateRequest commentCreateRequest) {

        Member member = memberRepository.findById(commentCreateRequest.getMember()).get();
        Board board = boardRepository.findById(commentCreateRequest.getBoard()).get();
        Optional<Board> optionalBoardEntity = boardRepository.findById(commentCreateRequest.getBoard());

        if (optionalBoardEntity.isPresent()) {
            return commentRepository.save(commentCreateRequest.toEntity(member, board)).getId();
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public CommentResponse findOne(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));

        return CommentResponse.from(comment);
    }

    @Transactional
    public void update(Long id, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        comment.update(commentUpdateRequest.getContent());
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAll() {
        return commentRepository.findAll().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}