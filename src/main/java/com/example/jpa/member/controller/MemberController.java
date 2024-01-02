package com.example.jpa.member.controller;

import java.util.List;

import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        List<MemberResponse> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    @PatchMapping()
    public ResponseEntity<MemberResponse> update(@PathVariable("id") Long id, @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.update(id, memberUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }

}
