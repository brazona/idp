package br.brazona.idp.api.infrastructure.config.security;

import br.brazona.idp.api.domain.services.business.AuthService;
import br.brazona.idp.api.domain.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.filters.CorsFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
* 
* Class that configuration security application.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Configuration
@EnableWebSecurity
@ComponentScan("br.brazona.idp.api.domain.config.security")
public class SecurityConfig {

    private final static String SERVICE_LOG = "Service started SecurityConfig: {}";
    Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    private static final String[] PUBLIC = {
            "/api/v1/auth/authentication"
            ,"/v1/auth/authentication"
    };

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;
    /**
     *
     * Method constructor.
     *
     **/
    public SecurityConfig() {
    }

    /**
     *
     * Method that intercepts request to apply security strategy.
     *
     * @param http class HttpSecurity
     * @return SecurityFilterChain, security class.
     *
     **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                     authorizeHttpRequests
                             .requestMatchers(PUBLIC).permitAll()
                             .anyRequest().access(customAuthManager()));
        return http.build();
    }
    /**
     *
     * Method that implements color rules to access the application.
     *
     * @return CorsConfigurationSource, CORS configuration class.
     *
     **/
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
    /**
     *
     * Method that implements color rules to access the application.
     *
     * @return FilterRegistrationBean, CORS configuration class.
     *
     **/
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean
                = new FilterRegistrationBean<>(new CorsFilter());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * Method that implements user authorization rules.
     *
     * @return AuthorizationManager, AUTHORIZATION configuration class.
     *
     **/
    AuthorizationManager<RequestAuthorizationContext> customAuthManager() {
        return new AuthorizationManager<RequestAuthorizationContext>() {
            
            @Override
            public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

                HttpServletRequest request = object.getRequest();
                String path =  request.getRequestURI();
                if (ArrayUtils.contains(PUBLIC, path)){
                    logger.info(SERVICE_LOG, "router public");
                    return new AuthorizationDecision(true);
                }
                String jwt = parseJwt(object.getRequest());

                if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);

                    UserDetails userDetails = authService.loadUserByUsername(username);

                    return new AuthorizationDecision(
                            authService.authorization(userDetails.getUsername())
                    );
                }

                return new AuthorizationDecision(false);
            }
        };
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
    /**
     *
     * Method that implements user authorization rules.
     *
     * @param authenticationConfiguration, object class that authentication config.
     * @return AuthenticationManager, AUTHENTICATION configuration class.
     *
     **/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}