package com.quizwhiz.quizapp1.service;

import com.quizwhiz.quizapp1.dao.QuestionDao;
import com.quizwhiz.quizapp1.dao.QuizDao;
import com.quizwhiz.quizapp1.model.Question;
import com.quizwhiz.quizapp1.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Quizservice {

    @Autowired
    QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuizByCategory(String category, int noOfQ, String title) {

        List<Question> questions = questionDao.findQuestionsRandomByCategroy(category, noOfQ, title);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuetions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }
}
