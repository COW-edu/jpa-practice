package com.example.jpa.member.controller;

import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MemberResponse> findOne(@PathVariable("id") Long id) {

        MemberResponse memberResponse = memberService.findOne(id);

        return ResponseEntity.ok(memberResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable("id") Long id, @RequestBody MemberUpdateRequest memberUpdateRequest) {

        memberService.update(id, memberUpdateRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponse> delete(@PathVariable("id") Long id) {

        memberService.delete(id);

        return ResponseEntity.ok().build();
    }
}
