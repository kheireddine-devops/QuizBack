package com.quiz.quizback.services.specs;

import com.quiz.quizback.dtos.requests.AuthRequest;
import com.quiz.quizback.dtos.responses.AuthResponse;
import com.quiz.quizback.domain.entities.User;

import java.util.Optional;

public interface IUserService {
    AuthResponse auth(AuthRequest requestDto);
    Optional<User> getByEmail(String email);
}
