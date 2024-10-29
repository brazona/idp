package br.brazona.idp.api.core.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
@Slf4j
@Component
public class AuthorizationProvider implements AuthorizationManager {
    @Override
    public AuthorizationDecision check(Supplier authentication, Object object) {
        ///Authorization
        log.info("AuthorizationProvider");
        return new AuthorizationDecision(true);
    }

    @Override
    public void verify(Supplier authentication, Object object) {
        log.info("AuthorizationProvider");
        AuthorizationManager.super.verify(authentication, object);
    }
}
