package com.quiz.quizback.dtos.responses;

import com.quiz.quizback.domain.enums.GenderEnum;
import com.quiz.quizback.domain.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString
public class UserResponseDto implements Serializable {
    private String id;
    private String firstname;
    private String lastname;
    private Date birthDate;
    private GenderEnum gender;
    private String email;
    private RoleEnum role;
}
