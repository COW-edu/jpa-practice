package com.example.jpa.board.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.board.repository.BoardRepository;
import com.example.jpa.member.service.MemberService;
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
    private final MemberService memberService;

    @Transactional
    public void createBoard(BoardCreateRequest boardCreateRequest) {
        boardRepository.save(Board.of(boardCreateRequest, memberService.findMember(boardCreateRequest.getId())));
    }

    @Transactional(readOnly = true)
    public BoardResponse retrieveBoardById(Long id) {
        return BoardResponse.from(checkExist(id));
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> retrieveBoards() {
        return boardRepository.findAll().stream().map(BoardResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.delete(checkExist(id));
    }

    @Transactional
    public void updateBoard(BoardUpdateRequest boardUpdateRequest) {
        Board targetBoard = checkExist(boardUpdateRequest.getId());
        targetBoard.update(boardUpdateRequest);
    }

    private Board checkExist(Long id) {
        Board targetBoard = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시판이 존재하지 않습니다."));
        return targetBoard;
    }
}
