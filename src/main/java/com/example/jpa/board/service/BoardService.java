package com.example.jpa.board.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.board.repository.BoardRepository;
import com.example.jpa.comment.domain.Comment;
import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.repository.CommentRepository;
import com.example.jpa.member.domain.Member;
import com.example.jpa.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void create(BoardCreateRequest boardCreateRequest) {
        Member member = memberRepository.findById(boardCreateRequest.getMember()).get();
        boardRepository.save(boardCreateRequest.toEntity(member));
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        List<String> comments = commentRepository.findByBoardId(id)
                .stream()
                .map(Comment::getBody)
                .collect(Collectors.toList());

        return BoardResponse.from(board, board.getMember().getName(), comments);
    }

    @Transactional
    public void update(Long id, BoardUpdateRequest boardUpdateRequest) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        board.update(boardUpdateRequest.getTitle(), boardUpdateRequest.getBody());
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}