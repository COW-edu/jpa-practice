package com.example.jpa.member.controller;

import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MemberCreateRequest memberCreateRequest) {
        memberService.create(memberCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        MemberResponse memberResponse = memberService.findOne(id);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/findAll")
    public ResponseEntity<MemberResponse> findAll() {
        List<MemberResponse> memberResponseList = memberService.findAll();
        return ResponseEntity.ok((MemberResponse) memberResponseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(memberUpdateRequest);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<MemberResponse> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
