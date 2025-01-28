package br.brazona.idp.api.infrastructure.config.security;

import br.brazona.idp.api.domain.exceptions.AccessDeniedException;
import br.brazona.idp.api.domain.services.business.AuthService;
import br.brazona.idp.api.domain.services.business.UserService;
import br.brazona.idp.api.domain.utils.EnvUtil;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Enumeration;
import java.util.function.Supplier;

@Component
@Configuration
public class CustomAuthManager implements AuthorizationManager {

    private static final int BASIC_LENGTH = 6;
    private static final int BEARER_LENGTH = 7;
    private static final String BASIC_USERNAME = "idp.security.basic.username";
    private static final String BASIC_PASSWORD = "idp.security.basic.password";
    private static final String[] PUBLIC = {
            "/api/v1/auth/authentication"
            ,"/v1/auth/authentication"
            ,"/v1/auth/forgot"
            ,"/api/v1/auth/forgot"
            ,"/api/v1/auth/validate/code"
            ,"/v1/auth/validate/code"
    };
    private static final String[] AUTHORIZATION = {
            "/v1/auth/update/password"
            ,"/api/v1/auth/update/password"
            ,"/api/v1/auth/authorization"
            ,"/v1/auth/authorization"
    };
    @Autowired
    private EnvUtil envUtil;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    AuthorizationManager<RequestAuthorizationContext> customAuthManager() {
        return new AuthorizationManager<RequestAuthorizationContext>() {
            @Override
            public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
                Enumeration<String> h = object.getRequest().getHeaderNames();
                String headerAuth = object.getRequest().getHeader("Authorization");
                String path =  object.getRequest().getRequestURI();
                Boolean isAuthorization = ArrayUtils.contains(AUTHORIZATION, path);
                if (headerAuth == null || headerAuth.isEmpty())
                    return new AuthorizationDecision(false);
                else if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
                    return authorizationBearer(headerAuth, isAuthorization);
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
     * Determines if access is granted for a specific authentication and object.
     *
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param object         the {@link T} object to check
     * @return an {@link AuthorizationDecision} or null if no decision could be made
     */
    @Override
    public AuthorizationDecision check(Supplier authentication, Object object) {
        return null;
    }

    /**
     * Determines if access should be granted for a specific authentication and object.
     *
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param object         the {@link T} object to check
     * @throws AccessDeniedException if access is not granted
     */
    @Override
    public void verify(Supplier authentication, Object object) {
        AuthorizationManager.super.verify(authentication, object);
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
    private AuthorizationDecision authorizationBearer(String auth, Boolean isAuthorization){
        String jwt = auth.substring(BEARER_LENGTH);
        if (jwt.isEmpty() || !jwtUtils.validateJwtToken(jwt))
            return new AuthorizationDecision(false);

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        UserDetails userDetails = authService.loadUserByUsername(username);
        UserDetailsVO userDetailsVO = userService.getUserByUsername(username);
        return new AuthorizationDecision(
                authService.authorization(userDetailsVO.getId(), jwt, isAuthorization));
    }
}
