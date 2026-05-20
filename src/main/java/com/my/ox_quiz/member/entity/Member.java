package com.my.ox_quiz.member.entity;

import com.my.ox_quiz.quiz.entity.Quiz;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends com.my.ox_quiz.member.entity.QuizBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false, unique = true, length = 30)
    private String id;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role = RoleType.USER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status = MemberStatus.PENDING;

    private Integer answerTrue;
    private Integer answerFalse;

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes = new ArrayList<>();

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        quiz.setMember(this);
    }
}
