package com.quiz.quizback.controllers.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.config.security.CustomJwtAuthenticationToken;
import com.quiz.quizback.controllers.specs.IResultController;
import com.quiz.quizback.domain.entities.Result;
import com.quiz.quizback.services.specs.IResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResultController implements IResultController {
    private final IResultService resultService;

    @Override
    public ResponseEntity<List<Result>> getCurrentUserResults(Principal principal) {
        try {
            CustomJwtAuthenticationToken token = (CustomJwtAuthenticationToken)principal;
            return new ResponseEntity<>(this.resultService.getUserResults(token.getUid()), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Boolean> checkTestTaker(String categoryId, Principal principal) {
        try {
            CustomJwtAuthenticationToken token = (CustomJwtAuthenticationToken)principal;
            return new ResponseEntity<>(this.resultService.checkTestTaker(token.getUid(),categoryId), HttpStatus.OK);
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
    public ResponseEntity<List<Result>> getAllResults() {
        return new ResponseEntity<>(this.resultService.getAllResults(), HttpStatus.OK);
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
