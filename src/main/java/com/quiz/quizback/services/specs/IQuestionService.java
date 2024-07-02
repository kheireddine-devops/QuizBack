package com.quiz.quizback.services.specs;

import com.quiz.quizback.domain.entities.Question;

public interface IQuestionService {
    void addQuestion(Question question);
    void updateQuestion(String questionId, Question question);
    void deleteQuestion(String categoryId,  String questionId);
}
