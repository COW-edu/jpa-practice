package com.example.jpa.board.controller;

import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {
        boardService.createBoard(boardCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> retrieveBoardById(@PathVariable("id") Long id) {
        BoardResponse boardresponse = boardService.retrieveBoardById(id);
        return ResponseEntity.ok(boardresponse);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> retrieveBoards() {
        return ResponseEntity.ok(boardService.retrieveBoards());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateBoard(@RequestBody BoardUpdateRequest boardUpdateRequest) {
        boardService.updateBoard(boardUpdateRequest);
        return ResponseEntity.ok().build();
    }

}
