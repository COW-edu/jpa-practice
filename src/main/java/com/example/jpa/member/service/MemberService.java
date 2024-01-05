package com.example.jpa.member.service;

import com.example.jpa.exception.DuplicateMemberException;
import com.example.jpa.member.domain.Member;
import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.member.repository.MemberRepository;

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
        checkDuplicate(memberCreateRequest);
        memberRepository.save(memberCreateRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public MemberResponse findOne(Long id) {
        return MemberResponse.from(checkExist(id));
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
        memberRepository.delete(checkExist(id));
    }

    public Member checkExist(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다."));
        return member;
    }

    private void checkDuplicate(MemberCreateRequest request) {
        Optional<Member> member = memberRepository.findByName(request.getName());
        member.ifPresent(m -> {
            try {
                m.hasSamePhoneNumber(request.getPhoneNumber());
            } catch (DuplicateMemberException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
