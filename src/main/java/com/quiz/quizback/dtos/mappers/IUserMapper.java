package com.quiz.quizback.dtos.mappers;

import com.quiz.quizback.domain.entities.User;
import com.quiz.quizback.dtos.requests.UserRequestDto;
import com.quiz.quizback.dtos.responses.UserResponseDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toEntity(UserRequestDto requestDto);
    UserResponseDto toDto(User entity);
}
