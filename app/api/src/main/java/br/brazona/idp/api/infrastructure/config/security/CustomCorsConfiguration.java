package br.brazona.idp.api.infrastructure.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomCorsConfiguration implements CorsConfigurationSource {
    private static final String[] ORIGIN = {
            "http://localhost:4200" , "http://127.0.0.1:4200"
    };
    /**
     * Return a {@link CorsConfiguration} based on the incoming request.
     *
     * @param request sss
     * @return the associated {@link CorsConfiguration}, or {@code null} if none
     */
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setMaxAge(3600L);

        if ("OPTIONS".equals(request.getMethod())) {
            corsConfig.setAllowedOrigins(List.of(request.getHeader("Origin")));
            corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"));
        } else {
            corsConfig.setAllowedOrigins(List.of(ORIGIN));
            corsConfig.setAllowCredentials(true);
            corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        }
        return corsConfig;

    }
}
