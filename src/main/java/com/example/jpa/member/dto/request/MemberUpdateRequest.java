package com.example.jpa.member.dto.request;

import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    private Long id;
    private String name;
    private int age;
    private String phoneNumber;

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .age(this.age)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
