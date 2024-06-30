package com.quiz.quizback.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AuthRequestDto {
    private String login;
    private String password;
}
