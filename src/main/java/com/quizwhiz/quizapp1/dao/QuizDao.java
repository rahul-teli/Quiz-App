package com.quizwhiz.quizapp1.dao;

import com.quizwhiz.quizapp1.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
