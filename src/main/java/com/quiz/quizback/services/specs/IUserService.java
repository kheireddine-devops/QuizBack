package com.quiz.quizback.services.specs;

import com.quiz.quizback.dtos.requests.AuthRequestDto;
import com.quiz.quizback.dtos.requests.UserRequestDto;
import com.quiz.quizback.dtos.responses.AuthResponseDto;
import com.quiz.quizback.domain.entities.User;
import com.quiz.quizback.dtos.responses.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    AuthResponseDto auth(AuthRequestDto requestDto);
    Optional<User> getByEmail(String email);
    UserResponseDto register(UserRequestDto requestDto);
    Page<UserResponseDto> getAllUsers(Pageable pageable);
}
