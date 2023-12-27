package com.example.jpa.reply.controller;

import com.example.jpa.reply.domain.Reply;
import com.example.jpa.reply.dto.request.ReplyCreateRequest;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import com.example.jpa.reply.dto.response.ReplyResponse;
import com.example.jpa.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Void> createReply(@RequestBody ReplyCreateRequest replyCreateRequest) {
        replyService.createReply(replyCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponse> retrieveReplyById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(replyService.retrieveReplyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReplyResponse>> retrieveReplies() {
        return ResponseEntity.ok(replyService.retrieveReplies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable("id") Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateReply(@RequestBody ReplyUpdateRequest replyUpdateRequest) {
        replyService.updateReply(replyUpdateRequest);
        return ResponseEntity.ok().build();
    }


}
