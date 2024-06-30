package com.quiz.quizback.services.impls;

import com.quiz.quizback.config.exceptions.CustomException;
import com.quiz.quizback.config.security.JwtTokenUtil;
import com.quiz.quizback.domain.entities.User;
import com.quiz.quizback.domain.enums.RoleEnum;
import com.quiz.quizback.dtos.mappers.IUserMapper;
import com.quiz.quizback.dtos.requests.AuthRequestDto;
import com.quiz.quizback.dtos.requests.UserRequestDto;
import com.quiz.quizback.dtos.responses.AuthResponseDto;
import com.quiz.quizback.dtos.responses.UserResponseDto;
import com.quiz.quizback.repositories.IUserRepository;
import com.quiz.quizback.services.specs.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Optional<User> getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public AuthResponseDto auth(AuthRequestDto requestDto) {
        if (requestDto.getLogin() == null || requestDto.getPassword() == null) {
            throw new RuntimeException("Missing login or password");
        } else {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getLogin());
            AuthResponseDto authResponse = new AuthResponseDto();
            final String jwtAccessToken = jwtTokenUtil.generateAccessToken(userDetails);
            authResponse.setToken(jwtAccessToken);
            return authResponse;
        }

    }

    @Override
    public UserResponseDto register(UserRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new CustomException("Email is already in use");
        }

        User user = this.userMapper.toEntity(requestDto);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole(RoleEnum.ROLE_USER);

        user = this.userRepository.save(user);
        return this.userMapper.toDto(user);
    }
}
