package com.example.jpa.member.domain;

import com.example.jpa.member.dto.request.MemberUpdateRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder
    public Member(Long id, String name) {
        this.name = name;
    }

    public void update(MemberUpdateRequest memberUpdateRequest) {
        this.name = memberUpdateRequest.getName();
    }
}
