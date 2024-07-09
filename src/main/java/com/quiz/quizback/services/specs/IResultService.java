package com.quiz.quizback.services.specs;

import com.quiz.quizback.domain.entities.Result;

import java.util.List;


public interface IResultService {
    boolean checkTestTaker(String userId, String categoryId);
    Result addResult(Result result);
    void deleteResult( String resultId);
    List<Result> getAllResults();
    List<Result> getUserResults(String uid);
}
