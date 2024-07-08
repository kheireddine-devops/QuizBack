package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.domain.entities.Question;
import com.quiz.quizback.domain.entities.Result;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Result")
public interface IResultController {
    @GetMapping("/{resultId}")
    ResponseEntity<Boolean> checkTestTaker(@PathVariable("resultId") String resultId , @RequestBody String userId);
    @PostMapping("")
    ResponseEntity<Result> addResult(@RequestBody @Valid Result result);
    @GetMapping("")
    ResponseEntity<List<Result>> getAllResults();
    @DeleteMapping("/{resultId}")
    ResponseEntity<Void> deleteResult(@PathVariable("resultId") String resultId);
}
