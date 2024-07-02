package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.domain.entities.Question;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/question")
public interface IQuestionController {
    @PostMapping("")
    ResponseEntity<Void> addQuestion(@RequestBody @Valid Question question);
    @PutMapping("/{questionId}")
    ResponseEntity<Void> updateQuestion(@PathVariable("questionId") String questionId, @RequestBody @Valid Question question);
    @DeleteMapping("/{categoryId}")
     ResponseEntity<Void> deleteQuestion(@PathVariable("categoryId") String categoryId, @RequestBody String questionId);
}
