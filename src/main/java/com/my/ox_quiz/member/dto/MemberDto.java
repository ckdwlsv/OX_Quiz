package com.my.ox_quiz.member.dto;

import com.my.ox_quiz.member.entity.RoleType;
import com.my.ox_quiz.member.entity.StatusType;
import com.my.ox_quiz.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long no;
    private String id;
    private String password;
    private RoleType role;
    private StatusType status;
    private int answerTrue;
    private int answerFalse;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static MemberDto toDto(Member member) {
        return new MemberDto(
                member.getNo(),
                member.getId(),
                member.getPassword(),
                member.getRole(),
                member.getStatus(),
                member.getAnswerTrue(),
                member.getAnswerFalse(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    public static Member toEntity(MemberDto dto) {
        return Member.builder()
                .no(dto.getNo())
                .id(dto.getId())
                .password(dto.getPassword())
                .role(dto.getRole())
                .status(dto.getStatus())
                .answerTrue(dto.getAnswerTrue())
                .answerFalse(dto.getAnswerFalse())
                .build();
    }
}
