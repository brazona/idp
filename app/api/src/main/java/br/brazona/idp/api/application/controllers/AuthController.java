package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.application.interfaces.IAuthController;
import br.brazona.idp.api.domain.services.business.AuthService;
import br.brazona.idp.api.domain.services.business.SessionService;
import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import br.brazona.idp.api.domain.views.business.SessionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController implements IAuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private SessionService sessionService;

    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public ResponseEntity<AuthResponseBusinessVO> authentication(AuthRequestBusinessVO auth) {
        log.info("endpoint: /v1/auth/authentication");
        AuthResponseBusinessVO tokenDTO = service.authentication(auth);
        sessionService.createUpdate(new SessionVO(auth.getUsername(), tokenDTO.getToken()));
        return ResponseEntity.ok()
                .header("Authorization", tokenDTO.getToken())
                .body(tokenDTO);
    }
}
