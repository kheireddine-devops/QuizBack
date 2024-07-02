package com.quiz.quizback.repositories;

import com.quiz.quizback.domain.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends MongoRepository<Category,String> {
}
