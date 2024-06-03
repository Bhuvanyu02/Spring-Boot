package com.bhuvi.quizapp.dao;

import com.bhuvi.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category= :category limit :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category,@Param("numQ") Integer numQ);
}
