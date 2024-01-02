package com.example.jpa.member.dto.request;

import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    private String name;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .build();
    }
}
