package com.quiz.quizback.services.impls;

import com.quiz.quizback.config.security.JwtTokenUtil;
import com.quiz.quizback.dtos.requests.AuthRequest;
import com.quiz.quizback.dtos.responses.AuthResponse;
import com.quiz.quizback.domain.entities.User;
import com.quiz.quizback.repositories.IUserRepository;
import com.quiz.quizback.services.specs.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    @Override
    public Optional<User> getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthResponse auth(AuthRequest requestDto) {
        if (requestDto.getLogin() == null || requestDto.getPassword() == null) {
            throw new RuntimeException("AUTH_ACTION_MISSING_LOGIN_OR_PASSWORD");
        } else {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getLogin());
            AuthResponse authResponse = new AuthResponse();
            final String jwtAccessToken = jwtTokenUtil.generateAccessToken(userDetails);
            authResponse.setToken(jwtAccessToken);
            return authResponse;
        }

    }
}
