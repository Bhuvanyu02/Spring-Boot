package com.bhuvi.quizapp.service;

import com.bhuvi.quizapp.dao.QuestionDao;
import com.bhuvi.quizapp.dao.QuizDao;
import com.bhuvi.quizapp.model.Question;
import com.bhuvi.quizapp.model.QuestionWrapper;
import com.bhuvi.quizapp.model.Quiz;
import com.bhuvi.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        Quiz quiz = new Quiz();

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);

        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz= quizDao.findById(id);
        List<Question> quesFromDB=quiz.get().getQuestions();

        List<QuestionWrapper>quesForUser= new ArrayList<>();

        for (Question q: quesFromDB){
            QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestion_title());
            quesForUser.add(qw);
        }

        return new ResponseEntity<>(quesForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> ques=quiz.getQuestions();
        int right=0;
        int i=0;
        for (Response response: responses){
            if (response.getResponse().equals(ques.get(i).getRight_answer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<Integer>(right,HttpStatus.OK);
    }
}
