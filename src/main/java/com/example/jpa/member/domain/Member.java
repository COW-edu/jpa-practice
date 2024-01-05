package com.example.jpa.member.domain;

import com.example.jpa.board.domain.Board;
import com.example.jpa.exception.DuplicateMemberException;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import com.example.jpa.reply.domain.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;
    private String phoneNumber;

    @OneToMany(mappedBy = "writer", orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

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

    public void hasSamePhoneNumber(String phoneNumber) throws DuplicateMemberException {
        if(this.phoneNumber.equals(phoneNumber)) {
            throw new DuplicateMemberException("이미 가입한 사용자입니다.");
        }
    }
}
