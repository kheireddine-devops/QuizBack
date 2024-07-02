package com.quiz.quizback.repositories;

import com.quiz.quizback.domain.entities.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface IResultRepository  extends MongoRepository<Result,String> {
    Optional<Result> findByUserId(String userId);
}
