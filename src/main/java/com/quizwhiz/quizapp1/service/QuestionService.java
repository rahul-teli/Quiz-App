package com.quizwhiz.quizapp1.service;

import com.quizwhiz.quizapp1.dao.QuestionDao;
import com.quizwhiz.quizapp1.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllTheQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Question added";
    }
}
