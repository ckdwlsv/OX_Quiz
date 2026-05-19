package com.my.ox_quiz.quiz.repository;


import com.my.ox_quiz.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
