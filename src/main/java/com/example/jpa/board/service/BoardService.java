package com.example.jpa.board.service;

import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void create(BoardCreateRequest boardCreateRequest) {
        boardRepository.save(boardCreateRequest.toE);
    }
}
