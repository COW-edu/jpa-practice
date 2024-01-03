package com.example.jpa.reply.service;

import com.example.jpa.member.domain.Member;
import com.example.jpa.reply.domain.Reply;
import com.example.jpa.reply.dto.request.ReplyCreateRequest;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import com.example.jpa.reply.dto.response.ReplyResponse;
import com.example.jpa.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ReplyService {
    private ReplyRepository replyRepository;

    @Transactional
    public void create(ReplyCreateRequest replyCreateRequest) {
        replyRepository.save(replyCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public ReplyResponse findOne(Long id) {
        Reply reply = checkReply(id);
        return ReplyResponse.from(reply);
    }

    @Transactional(readOnly = true)
    public Reply checkReply(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        return reply;
    }

    @Transactional
    public void updateReply(ReplyUpdateRequest replyUpdateRequest) {
        Reply reply = checkReply(replyUpdateRequest.getId());
        reply.update(replyUpdateRequest);
    }

    @Transactional(readOnly = true)
    public void deleteReply(Long id) {
        replyRepository.delete(checkReply(id));
    }
}
