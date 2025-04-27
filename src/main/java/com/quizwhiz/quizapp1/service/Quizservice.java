package com.quizwhiz.quizapp1.service;

import com.quizwhiz.quizapp1.dao.QuestionDao;
import com.quizwhiz.quizapp1.dao.QuizDao;
import com.quizwhiz.quizapp1.model.Question;
import com.quizwhiz.quizapp1.model.QuestionWrapper;
import com.quizwhiz.quizapp1.model.Quiz;
import com.quizwhiz.quizapp1.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuetions();
        List<QuestionWrapper> questionsforUser = new ArrayList<>();

        for(Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4() );
            questionsforUser.add(qw);
        }

        return new ResponseEntity<>(questionsforUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuetions();
        int right =0;
        int i=0;
        for (Response r : responses) {
            if(r.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
