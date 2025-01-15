package br.brazona.idp.api.infrastructure.config.security;

import br.brazona.idp.api.domain.services.business.AuthService;
import br.brazona.idp.api.domain.services.business.UserService;
import br.brazona.idp.api.domain.utils.EnvUtil;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.filters.CorsFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
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
    private static final int BASIC_LENGTH = 6;
    private static final int BEARER_LENGTH = 7;
    private static final String BASIC_USERNAME = "idp.security.basic.username";
    private static final String BASIC_PASSWORD = "idp.security.basic.password";
    @Autowired
    private EnvUtil envUtil;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomCorsConfiguration customCorsConfiguration;
    @Autowired
    private CustomAuthManager customAuthManager;
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
                .cors(corsConfigurer ->
                        corsConfigurer.configurationSource(customCorsConfiguration))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                     authorizeHttpRequests
                             .anyRequest().access(customAuthManager.customAuthManager()));
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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