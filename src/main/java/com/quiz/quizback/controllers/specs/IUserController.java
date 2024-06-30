package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.dtos.requests.AuthRequestDto;
import com.quiz.quizback.dtos.requests.UserRequestDto;
import com.quiz.quizback.dtos.responses.AuthResponseDto;
import com.quiz.quizback.dtos.responses.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping()
public interface IUserController {

    @PostMapping("/auth")
    ResponseEntity<AuthResponseDto> auth(@RequestBody AuthRequestDto requestDto);

    @PostMapping("/register")
    ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto requestDto);

    @GetMapping("/users/all")
    ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);
}
