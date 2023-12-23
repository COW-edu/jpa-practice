package com.example.jpa.member.service;

import com.example.jpa.member.domain.Member;
import com.example.jpa.member.dto.request.MemberCreateRequest;
import com.example.jpa.member.dto.response.MemberResponse;
import com.example.jpa.repository.MemberRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
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


}
