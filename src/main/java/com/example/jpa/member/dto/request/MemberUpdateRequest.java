package com.example.jpa.member.dto.request;

import com.example.jpa.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberUpdateRequest {

    private Long id;
    private String name;
}
