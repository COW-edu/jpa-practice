package com.example.jpa.member.dto.request;

import com.example.jpa.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private int age;

    @NotBlank
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
