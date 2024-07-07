package com.quiz.quizback.config.properities;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "quiz")
public record QuizConfig(Security security,Allow allow) {
    record Security(Rsa rsa,Jwt jwt) {
        record Rsa(RSAPublicKey publicKey, RSAPrivateKey privateKey) {}
        record Jwt(Token token,AccessToken accessToken, RefreshToken refreshToken,Claims claims) {
            record Token(Long expiration){}
            record AccessToken(Long expiration){}
            record RefreshToken(Long expiration){}
            record Claims(Names names,Values values){
                record Values(
                        String issuer
                ){}
                record Names(
                        String authorities,
                        String userId,
                        String userEmail,
                        String userPermissions
                ){}
            }
        }
    }
    record Allow(List<String> origin) {}
}
