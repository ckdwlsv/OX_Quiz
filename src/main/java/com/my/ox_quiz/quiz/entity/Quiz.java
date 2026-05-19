package com.my.ox_quiz.quiz.entity;

import com.my.ox_quiz.member.entity.QuizBaseEntity;
import com.my.ox_quiz.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Quiz extends QuizBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 100)
    private String content;
    @Column(nullable = false)
    private boolean answer;
    private String writer;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
