package com.example.jpa.member.service;

import com.example.jpa.member.domain.Member;
import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.repository.BoardRepository;
import com.example.jpa.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void create(MemberCreateRequest memberCreateRequest) {
        memberRepository.save(memberCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public MemberResponse findOne(Long id) {
        Member member = checkMember(id);
        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public Member checkMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        return member;
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        List<Member> memberListTemp = memberRepository.findAll();
        List<MemberResponse> memberResponseList = memberListTemp.stream().map(MemberResponse::from).toList();
        return memberResponseList;
    }

    @Transactional
    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        Member member = checkMember(memberUpdateRequest.getId());
        member.update(memberUpdateRequest.getName());
    }

    @Transactional(readOnly = true)
    public void deleteMember(Long id) {
        memberRepository.delete(checkMember(id));
    }
}
