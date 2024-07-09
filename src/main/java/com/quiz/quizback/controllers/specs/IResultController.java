package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.domain.entities.Question;
import com.quiz.quizback.domain.entities.Result;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/result")
public interface IResultController {
    @GetMapping("/{categoryId}")
    ResponseEntity<Boolean> checkTestTaker(@PathVariable("categoryId") String categoryId, Principal principal);
    @PostMapping("")
    ResponseEntity<Result> addResult(@RequestBody @Valid Result result);
    @GetMapping()
    ResponseEntity<List<Result>> getCurrentUserResults(Principal principal);
    @GetMapping("/all")
    ResponseEntity<List<Result>> getAllResults();
    @DeleteMapping("/{resultId}")
    ResponseEntity<Void> deleteResult(@PathVariable("resultId") String resultId);
}
