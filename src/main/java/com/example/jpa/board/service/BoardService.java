package com.example.jpa.board.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.board.repository.BoardRepository;
import com.example.jpa.member.domain.Member;
import com.example.jpa.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Transactional
    public void createBoard(BoardCreateRequest boardCreateRequest) {
        Member targetMember = memberService.checkExist(boardCreateRequest.getId());
        boardRepository.save(boardCreateRequest.toEntity(targetMember));
    }

    public BoardResponse retrieveBoardById(Long id) {
        return BoardResponse.from(checkExist(id));
    }

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

    public Board checkExist(Long id) {
        Board targetBoard = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시판이 존재하지 않습니다."));
        return targetBoard;
    }

}
