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

/**
* 
* Class that provides endpoint for user authentication and authorization.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Slf4j
@RestController
public class AuthController implements IAuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private SessionService sessionService;

    /**
     *
     * Class constructor.
     *
     **/
     public AuthController() {
    }

    /**
     * 
     * User authentication method, when valid the information provides an access token.
     * 
     * @param auth credentials for authentication, username and password for access registration.
     * @return a response with the token value
     *
     **/                                                                                                                        
    @Override
    public ResponseEntity<AuthResponseBusinessVO> authentication(AuthRequestBusinessVO auth) {
        log.info("endpoint: /v1/auth/authentication");
        AuthResponseBusinessVO tokenDTO = service.authentication(auth);
        sessionService.createUpdate(new SessionVO(auth.getUsername(), tokenDTO.getToken()));
        return ResponseEntity.ok()
                .header("Authorization", "Bearer "+tokenDTO.getToken())
                .body(tokenDTO);
    }
}
