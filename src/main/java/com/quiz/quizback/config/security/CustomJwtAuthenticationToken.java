package com.quiz.quizback.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Map;

@Getter @Setter
public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {
    private String principal;
    private String uid;
    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String principal,String uid, Map<String,Object> details) {
        super(jwt, authorities, principal);
        this.setPrincipal(principal);
        this.setUid(uid);
        this.setAuthenticated(true);
        this.setDetails(details);
    }
}
