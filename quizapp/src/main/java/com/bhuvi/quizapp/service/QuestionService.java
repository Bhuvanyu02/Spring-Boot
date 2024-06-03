package com.bhuvi.quizapp.service;

import com.bhuvi.quizapp.model.Question;
import com.bhuvi.quizapp.dao.QuestionDao;
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

    public ResponseEntity<List<Question>> getallQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String cat) {
       try{

           return new ResponseEntity<>(questionDao.findByCategory(cat),HttpStatus.OK);
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question q) {

        try{
            questionDao.save(q);
            return new ResponseEntity<>("Added Successfully",HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Added",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id){
        try{
            var byId = questionDao.findById(id);
            if (byId.isPresent()) {
                questionDao.deleteById(id);
                return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Deleted, Id Not found",HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Deleted, Id Not found",HttpStatus.NOT_FOUND);
    }

}
