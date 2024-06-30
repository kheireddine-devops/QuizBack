package com.quiz.quizback.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Value("${quiz.security.jwt.claims.names.authorities}") private String JWT_CLAIMS_NAMES_AUTHORITIES;
    @Value("${quiz.security.jwt.claims.names.user-id}") private String JWT_CLAIMS_NAMES_USER_ID;

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        String subject = source.getSubject();
        String uid = source.getClaimAsString(JWT_CLAIMS_NAMES_USER_ID);
        Collection<GrantedAuthority> authorities = source
                .getClaimAsStringList(JWT_CLAIMS_NAMES_AUTHORITIES)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Map<String,Object> details = Map.of(
            "uid", uid,
            "subject", subject
        );

        return new CustomJwtAuthenticationToken(source,authorities,subject,uid,details);
    }
}
