package com.example.jpa.board.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.dto.request.BoardCreateRequest;
import com.example.jpa.board.dto.request.BoardUpdateRequest;
import com.example.jpa.board.dto.response.BoardResponse;
import com.example.jpa.member.domain.Member;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.reply.domain.Reply;
import com.example.jpa.repository.BoardRepository;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void create(BoardCreateRequest boardCreateRequest) {
        Member member = memberRepository.findById(boardCreateRequest.getMember().getId())
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));

        boardRepository.save(boardCreateRequest.toEntity(member));
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        Board board = checkBoard(id);

        List<Reply> replyList = replyRepository.findByBoardId(id);
        board.setReplyList(replyList);

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
        Board board = checkBoard(id);

        List<Reply> replyList = replyRepository.findByBoardId(id);
        board.setReplyList(replyList);

        boardRepository.delete(board);
    }
}
