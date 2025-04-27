package com.quizwhiz.quizapp1.dao;

import com.quizwhiz.quizapp1.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);
    @Query(value = "SELECT *  FROM question q Where q.category=:category ORDER BY RAND() LIMIT :noOfQ", nativeQuery = true)
    List<Question> findQuestionsRandomByCategroy(String category, int noOfQ, String title);
}
