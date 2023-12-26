package com.example.jpa.member.service;

import com.example.jpa.member.domain.Member;
import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.member.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
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
        Member member = checkExist(id);

        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> retrieveMembers() {
        return memberRepository.findAll().stream().map(MemberResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        Member targetMember = checkExist(memberUpdateRequest.getId());
        targetMember.update(memberUpdateRequest);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member targetMember = checkExist(id);
        memberRepository.delete(targetMember);
    }

    private Member checkExist(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        return member;
    }
}
