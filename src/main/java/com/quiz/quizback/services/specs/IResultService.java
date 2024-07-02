package com.quiz.quizback.services.specs;

import com.quiz.quizback.domain.entities.Result;


public interface IResultService {
    boolean checkTestTaker( String resultId ,String userId);
    Result addResult(Result result);
    void deleteResult( String resultId);
}
