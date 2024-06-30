package com.quiz.quizback.dtos.requests;

import com.quiz.quizback.domain.enums.GenderEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString
public class UserRequestDto implements Serializable {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Firstname must be at least 2 characters long")
    private String firstname;


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Lastname must be at least 2 characters long")
    private String lastname;

    @NotNull
    @Past
    private Date birthDate;

    @NotNull
    private GenderEnum gender;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Password must be at least 6 characters long and contain at least one digit, one uppercase letter, and one lowercase letter.")
    private String password;
}
