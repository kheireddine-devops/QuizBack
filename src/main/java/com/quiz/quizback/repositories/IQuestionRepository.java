package com.quiz.quizback.repositories;

import com.quiz.quizback.domain.entities.Category;
import com.quiz.quizback.domain.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends MongoRepository<Question,String> {
}
