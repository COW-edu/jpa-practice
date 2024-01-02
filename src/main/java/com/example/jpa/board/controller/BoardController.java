package com.example.jpa.board.controller;

import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BoardCreateRequest boardCreateRequest) {

        boardService.create(boardCreateRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> findOne(@PathVariable("id") Long id) {

        BoardResponse boardResponse = boardService.findOne(id);

        return ResponseEntity.ok(boardResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody BoardUpdateRequest boardUpdateRequest) {

        boardService.update(id, boardUpdateRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        boardService.delete(id);

        return ResponseEntity.ok().build();
    }
}