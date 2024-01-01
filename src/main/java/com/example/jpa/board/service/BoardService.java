package com.example.jpa.board.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    @Transactional
    public void create(BoardCreateRequest boardCreateRequest) {
        boardRepository.save(boardCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        Board board = checkBoard(id);
        return BoardResponse.from(board);
    }

    @Transactional(readOnly = true)
    public Board checkBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시판 정보가 존재하지 않습니다."));
        return board;
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> findAll() {
        List<Board> boardListTemp = boardRepository.findAll();
        List<BoardResponse> boardResponseList = boardListTemp.stream().map(BoardResponse::from).toList();
        return boardResponseList;
    }

    @Transactional
    public void updateBoard(BoardUpdateRequest boardUpdateRequest) {
        Board board = checkBoard(boardUpdateRequest.getId());
        board.update(boardUpdateRequest);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.delete(checkBoard(id));
    }
}
