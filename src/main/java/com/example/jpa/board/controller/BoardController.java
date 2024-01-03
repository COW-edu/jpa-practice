package com.example.jpa.board.controller;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
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

    @GetMapping("/findAll")
    public ResponseEntity<BoardResponse> findAll() {
        List<BoardResponse> boardResponseList = boardService.findAll();
        return ResponseEntity.ok((BoardResponse) boardResponseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(@RequestBody BoardUpdateRequest boardUpdateRequest) {
        boardService.updateBoard(boardUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
