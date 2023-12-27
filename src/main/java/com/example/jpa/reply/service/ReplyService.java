package com.example.jpa.reply.service;

import com.example.jpa.board.domain.Board;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.member.domain.Member;
import com.example.jpa.member.service.MemberService;
import com.example.jpa.reply.domain.Reply;
import com.example.jpa.reply.dto.request.ReplyCreateRequest;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import com.example.jpa.reply.dto.response.ReplyResponse;
import com.example.jpa.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    @Transactional
    public void createReply(ReplyCreateRequest replyCreateRequest) {
        Member targetMember = memberService.checkExist(replyCreateRequest.getMember_id());
        Board targetBoard = boardService.checkExist(replyCreateRequest.getMember_id());
        replyRepository.save(Reply.of(replyCreateRequest, targetMember, targetBoard));
    }

    @Transactional(readOnly = true)
    public ReplyResponse retrieveReplyById(Long id) {
        return ReplyResponse.from(checkExist(id));
    }

    @Transactional(readOnly = true)
    public List<ReplyResponse> retrieveReplies() {
        return replyRepository.findAll().stream().map(ReplyResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteReply(Long id) {
        Reply targetReply = checkExist(id);
        replyRepository.delete(targetReply);
    }

    @Transactional
    public void updateReply(ReplyUpdateRequest replyUpdateRequest) {
        Reply targetReply = checkExist(replyUpdateRequest.getId());
        targetReply.update(replyUpdateRequest);
    }

    public Reply checkExist(Long id) {
        Reply targetReply = replyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시판이 존재하지 않습니다."));
        return targetReply;
    }

}
