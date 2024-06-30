package com.quiz.quizback.controllers.impls;

import com.quiz.quizback.controllers.specs.IUserController;
import com.quiz.quizback.dtos.requests.AuthRequestDto;
import com.quiz.quizback.dtos.requests.UserRequestDto;
import com.quiz.quizback.dtos.responses.AuthResponseDto;
import com.quiz.quizback.dtos.responses.UserResponseDto;
import com.quiz.quizback.services.specs.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {
    private final IUserService userService;
    @Override
    public ResponseEntity<AuthResponseDto> auth(AuthRequestDto requestDto) {
        return new ResponseEntity<>(this.userService.auth(requestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> register(UserRequestDto requestDto) {
        return new ResponseEntity<>(this.userService.register(requestDto), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(this.userService.getAllUsers(pageable), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> getUserById(String userId) {
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }
}
