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

    private int age;
    private String phoneNumber;


    @Builder
    public Member(Long id, String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public void update(MemberUpdateRequest memberUpdateRequest) {
        this.name = memberUpdateRequest.getName();
        this.age = memberUpdateRequest.getAge();
        this.phoneNumber = memberUpdateRequest.getPhoneNumber();
    }
}
