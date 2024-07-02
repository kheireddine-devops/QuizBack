package com.quiz.quizback.controllers.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.controllers.specs.IQuestionController;
import com.quiz.quizback.domain.entities.Question;
import com.quiz.quizback.services.specs.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController implements IQuestionController {
  private final IQuestionService questionService;
    @Override
    public ResponseEntity<Void> addQuestion(Question question) {
        try {
            questionService.addQuestion(question);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> updateQuestion(String questionId, Question question) {
        try {
            questionService.updateQuestion(questionId,question);
            return new ResponseEntity<>(HttpStatus.OK );
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(String categoryId, String questionId) {
        try {
            questionService.deleteQuestion(categoryId,questionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
