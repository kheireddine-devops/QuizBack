package com.quiz.quizback.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Configuration
public class CorsConfig {
    @Value("${quiz.allow.origin}")
    private List<String> allowOrigin;

    @Bean
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();


        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowOrigin);
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("PATCH");
        config.setAllowCredentials(true);

        if (!CollectionUtils.isEmpty(config.getAllowedOrigins())) {
            source.registerCorsConfiguration("/**", config);
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/health", config);
            source.registerCorsConfiguration("/info", config);
            source.registerCorsConfiguration("/v2/api-docs", config);
        }

        return new CorsFilter(source);
    }
}
