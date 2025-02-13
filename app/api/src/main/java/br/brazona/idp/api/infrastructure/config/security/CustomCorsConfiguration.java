package br.brazona.idp.api.infrastructure.config.security;

import br.brazona.idp.api.domain.utils.EnvUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomCorsConfiguration implements CorsConfigurationSource {
    @Autowired
    private EnvUtil envUtil;
    private static final String CORS_ORIGIN_PROP = "idp.cors.origin";
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
        String value = envUtil.getEnvValue(CORS_ORIGIN_PROP);
        String[] list_cors_origin = value.split(",");

        if ("OPTIONS".equals(request.getMethod())) {
            corsConfig.setAllowedOrigins(List.of(request.getHeader("Origin")));
            corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"));
        } else {
            corsConfig.setAllowedOrigins(List.of(list_cors_origin));
            corsConfig.setAllowCredentials(true);
            corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        }
        return corsConfig;

    }
}
