package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.dtos.requests.AuthRequestDto;
import com.quiz.quizback.dtos.requests.UserRequestDto;
import com.quiz.quizback.dtos.responses.AuthResponseDto;
import com.quiz.quizback.dtos.responses.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
public interface IUserController {

    @PostMapping("/auth")
    ResponseEntity<AuthResponseDto> auth(@RequestBody AuthRequestDto requestDto);

    @PostMapping("/register")
    ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto requestDto);
}
