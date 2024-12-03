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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Base64;
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

    private static final String[] PUBLIC = {
            "/api/v1/auth/authentication"
            ,"/v1/auth/authentication"
            ,"/v1/auth/forgot"
            ,"/api/v1/auth/forgot"
            ,"/v1/auth/update/password"
            ,"/api/v1/auth/update/password"
            ,"/api/v1/auth/update"
            ,"/v1/auth/update"
    };

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
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
                             //.requestMatchers(PUBLIC).permitAll()
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

                String headerAuth = object.getRequest().getHeader("Authorization");
                String path =  object.getRequest().getRequestURI();

                if (headerAuth == null || headerAuth.isEmpty())
                    return new AuthorizationDecision(false);
                else if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
                    return authorizationBearer(headerAuth);
                } else if (StringUtils.hasText(headerAuth)
                        && headerAuth.startsWith("Basic ") && ArrayUtils.contains(PUBLIC, path)) {
                    return authorizationBasic(headerAuth);
                }else {
                    return new AuthorizationDecision(false);
                }
            }
        };
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
    private AuthorizationDecision authorizationBasic(String auth){
        byte[] basicTokenDecoded = Base64.getDecoder().decode(auth.substring(BASIC_LENGTH));
        String username = envUtil.getEnvValue(BASIC_USERNAME);
        String password = envUtil.getEnvValue(BASIC_PASSWORD);

        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return new AuthorizationDecision(false);

        String basicTokenValue = new String(
                Base64.getDecoder().decode(auth.substring(BASIC_LENGTH)));

        if (!basicTokenValue.contains(":"))
            return new AuthorizationDecision(false);

        String[] basicAuthsSplit = basicTokenValue.split(":");

        if (!basicAuthsSplit[0].equals(username)
                || !basicAuthsSplit[1].equals(password)) {
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(true);
    }
    private AuthorizationDecision authorizationBearer(String auth){
        String jwt = auth.substring(BEARER_LENGTH);
        if (jwt.isEmpty() || !jwtUtils.validateJwtToken(jwt))
            return new AuthorizationDecision(false);

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        UserDetails userDetails = authService.loadUserByUsername(username);
        UserDetailsVO userDetailsVO = userService.getUserByUsername(username);
        return new AuthorizationDecision(
                authService.authorization(userDetailsVO.getId(), jwt));
    }
}