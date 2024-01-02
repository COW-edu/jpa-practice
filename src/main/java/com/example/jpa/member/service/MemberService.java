package com.example.jpa.member.service;

import com.example.jpa.member.domain.Member;
import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void create(MemberCreateRequest memberCreateRequest) {
        memberRepository.save(memberCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public MemberResponse findOne(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));

        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new NoSuchElementException("등록된 멤버가 없습니다.");
        }
        return members.stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        member.update(memberUpdateRequest);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}
