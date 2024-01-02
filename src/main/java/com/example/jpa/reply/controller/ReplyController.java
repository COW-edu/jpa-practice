package com.example.jpa.reply.controller;

import com.example.jpa.reply.dto.request.ReplyCreateRequest;
import com.example.jpa.reply.dto.request.ReplyUpdateRequest;
import com.example.jpa.reply.dto.response.ReplyResponse;
import com.example.jpa.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reply")
@RequiredArgsConstructor
public class ReplyController {
    private ReplyService replyService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReplyCreateRequest replyCreateRequest) {
        replyService.create(replyCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponse> findOne(@PathVariable("id") Long id) {
        ReplyResponse replyResponse = replyService.findOne(id);
        return ResponseEntity.ok(replyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReply(@RequestBody ReplyUpdateRequest replyUpdateRequest) {
        replyService.updateReply(replyUpdateRequest);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteReply(@PathVariable("id") Long id){
        replyService.deleteReply(id);
        return ResponseEntity.ok().build();
    }
}
