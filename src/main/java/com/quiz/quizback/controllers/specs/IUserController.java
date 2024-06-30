package com.quiz.quizback.controllers.specs;

import com.quiz.quizback.dtos.requests.AuthRequest;
import com.quiz.quizback.dtos.responses.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
public interface IUserController {

    @PostMapping("/auth")
    ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest requestDto);
}
