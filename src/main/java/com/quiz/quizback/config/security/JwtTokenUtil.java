package com.quiz.quizback.config.security;

import com.quiz.quizback.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${quiz.security.jwt.token.expiration}") private Long JWT_TOKEN_EXPIRATION;
    @Value("${quiz.security.jwt.access-token.expiration}") private Long JWT_ACCESS_TOKEN_EXPIRATION;
    @Value("${quiz.security.jwt.refresh-token.expiration}") private Long JWT_REFRESH_TOKEN_EXPIRATION;
    @Value("${quiz.security.jwt.claims.values.issuer}") private String JWT_CLAIMS_VALUES_ISSUER;
    @Value("${quiz.security.jwt.claims.names.authorities}") private String JWT_CLAIMS_NAMES_AUTHORITIES;
    @Value("${quiz.security.jwt.claims.names.user-id}") private String JWT_CLAIMS_NAMES_USER_ID;
    @Value("${quiz.security.jwt.claims.names.user-permissions}") private String JWT_CLAIMS_NAMES_USER_PERMISSIONS;


    public String generateAccessToken(UserDetails userDetails) {
        Instant instant = Instant.now();
        String scope = getAuthorities(userDetails);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuedAt(instant)
                .expiresAt(instant.plus(JWT_ACCESS_TOKEN_EXPIRATION, ChronoUnit.MINUTES))
                .issuer(JWT_CLAIMS_VALUES_ISSUER)
                .claim(JWT_CLAIMS_NAMES_USER_ID,((User)userDetails).getId())
                .claim(JWT_CLAIMS_NAMES_AUTHORITIES, scope)
                .claim(JWT_CLAIMS_NAMES_USER_PERMISSIONS, Collections.EMPTY_LIST)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuedAt(instant)
                .expiresAt(instant.plus(JWT_REFRESH_TOKEN_EXPIRATION, ChronoUnit.MINUTES))
                .issuer(JWT_CLAIMS_VALUES_ISSUER)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String generateToken(UserDetails userDetails) {
        Instant instant = Instant.now();
        String scope = getAuthorities(userDetails);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuedAt(instant)
                .expiresAt(instant.plus(JWT_TOKEN_EXPIRATION, ChronoUnit.MINUTES))
                .issuer(JWT_CLAIMS_VALUES_ISSUER)
                .claim(JWT_CLAIMS_NAMES_USER_ID,((User)userDetails).getId())
                .claim(JWT_CLAIMS_NAMES_AUTHORITIES, scope)
                .claim(JWT_CLAIMS_NAMES_USER_PERMISSIONS, Collections.EMPTY_LIST)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    private String getAuthorities(UserDetails userDetails) {
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).isAfter(Instant.now());
    }

    public Jwt decodeJwt(String token) {
        Jwt jwt;
        try {
            jwt = this.jwtDecoder.decode(token);
        }
        catch (JwtException exception) {
            throw new SecurityException("");
        }
        return jwt;
    }

    public String extractSubject(String token) {
        return this.decodeJwt(token).getSubject();
    }

    public boolean validateToken(String token) {
        return isTokenExpired(token);
    }

    public <R> R extractClaim(String token, String claim) {
        return this.decodeJwt(token).getClaim(claim);
    }

    public Map<? extends String,Object> extractAllClaims(String token) {
        return this.decodeJwt(token).getClaims();
    }

    public Instant extractExpiration(String token) {
        return this.decodeJwt(token).getExpiresAt();
    }
}