package com.quiz.quizback.controllers.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.controllers.specs.IResultController;
import com.quiz.quizback.domain.entities.Result;
import com.quiz.quizback.services.specs.IResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResultController implements IResultController {
    private final IResultService resultService;
    @Override
    public ResponseEntity<Boolean> checkTestTaker(String resultId, String userId) {
        try {
            return new ResponseEntity<>(this.resultService.checkTestTaker(resultId, userId), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Result> addResult(Result result) {
        try {
            return new ResponseEntity<>(this.resultService.addResult(result), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Void> deleteResult(String resultId) {
        try {
            resultService.deleteResult(resultId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
