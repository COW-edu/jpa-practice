package com.example.jpa.member.dto.response;

import com.example.jpa.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private int age;
    private String phoneNumber;

    public MemberResponse(Long id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getAge(), member.getPhoneNumber());
    }
}
