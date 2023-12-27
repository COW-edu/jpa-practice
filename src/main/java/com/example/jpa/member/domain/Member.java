package com.example.jpa.member.domain;

import com.example.jpa.board.domain.Board;
import com.example.jpa.member.dto.request.MemberUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

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

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();

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
