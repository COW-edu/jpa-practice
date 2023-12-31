package com.example.jpa.board.controller;

import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BoardCreateRequest boardCreateRequest) {
        boardService.create(boardCreateRequest);
        return ResponseEntity.ok().build();
    }
}
