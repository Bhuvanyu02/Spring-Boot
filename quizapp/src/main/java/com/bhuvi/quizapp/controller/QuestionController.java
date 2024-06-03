package com.bhuvi.quizapp.controller;


import com.bhuvi.quizapp.model.Question;
import com.bhuvi.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")

public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getallQuestions();
    }

    @GetMapping("category/{cat}")
    public  ResponseEntity<List<Question>> getQuationsByCategory(@PathVariable String cat){

            return  questionService.getQuestionsByCategory(cat);

    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question q){

        return questionService.addQuestion(q);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
         return questionService.deleteQuestion(id);
    }

}
