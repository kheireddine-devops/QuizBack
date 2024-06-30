package com.quiz.quizback.controllers.impls;

import com.quiz.quizback.controllers.specs.IUserController;
import com.quiz.quizback.dtos.requests.AuthRequest;
import com.quiz.quizback.dtos.responses.AuthResponse;
import com.quiz.quizback.services.specs.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {
    private final IUserService userService;
    @Override
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest requestDto) {
        return new ResponseEntity<>(this.userService.auth(requestDto), HttpStatus.OK);
    }
}
