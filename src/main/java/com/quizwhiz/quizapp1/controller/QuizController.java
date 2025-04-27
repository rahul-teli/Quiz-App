package com.quizwhiz.quizapp1.controller;

import com.quizwhiz.quizapp1.model.QuestionWrapper;
import com.quizwhiz.quizapp1.model.Response;
import com.quizwhiz.quizapp1.service.Quizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("quiz")
public class QuizController {
    @Autowired
    Quizservice quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noOfQ, @RequestParam String title ){
        return quizService.createQuizByCategory(category,noOfQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions  (@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestParam List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
