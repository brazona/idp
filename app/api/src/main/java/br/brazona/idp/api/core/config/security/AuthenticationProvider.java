package br.brazona.idp.api.core.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String userId = authentication.getName();
        final String password = authentication.getCredentials().toString();
        if (!"admin".equals(userId) || !"system".equals(password)) {
            return null;
        }
        log.info("AuthenticationProvider");
        log.info(userId);
        log.info(password);
        return authenticateAgainstThirdPartyAndGetAuthentication(userId, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.info("AuthenticationProvider");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password) {
        log.info("AuthenticationProvider");
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
}
