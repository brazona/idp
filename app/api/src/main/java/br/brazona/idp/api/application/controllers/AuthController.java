package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.application.interfaces.IAuthController;
import br.brazona.idp.api.domain.constants.EndpointsConst;
import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.constants.LogsConst;
import br.brazona.idp.api.domain.services.business.AuthService;
import br.brazona.idp.api.domain.services.business.SessionService;
import br.brazona.idp.api.domain.views.business.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    public AuthController(AuthService service, SessionService sessionService) {
        this.service = service;
        this.sessionService = sessionService;
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
        log.info(LogsConst.ENDPOINT_INFO, EndpointsConst.AUTH_AUTHENTICATION);
        log.debug(LogsConst.ENDPOINT_DEBUG, auth);
        AuthResponseBusinessVO tokenDTO = service.authentication(auth);
        sessionService.createUpdate(new SessionVO(auth.getUsername(), tokenDTO.getToken()));
        return ResponseEntity.ok()
                .header("Authorization", "Bearer "+tokenDTO.getToken())
                .body(tokenDTO);
    }

    /**
     * User authentication method, when valid the information provides an access token.
     *
     * @param token description
     * @return a response with the token value
     */
    @Override
    public ResponseEntity<AuthorizationResponseVO> authorization(String token) {
        log.info(LogsConst.ENDPOINT_INFO, EndpointsConst.AUTH_AUTHORIZATION);
        log.debug(LogsConst.ENDPOINT_DEBUG, "null", token);
        return ResponseEntity.ok().body(
                new AuthorizationResponseVO(true, ExceptionConst.AUTHORIZED));
    }

    /**
     * User authentication method, when valid the information provides an access token.
     *
     * @param auth  credentials for authentication, username and password for access registration.
     * @param token DDDD
     * @return a response with the token value
     **/
    @Override
    public ResponseEntity<ForgotResponseVO> forgotPassword(AuthRequestBusinessVO auth, String token) {
        log.info(LogsConst.ENDPOINT_INFO, EndpointsConst.AUTH_FORGOT);
        log.debug(LogsConst.ENDPOINT_DEBUG, auth, token);
        return ResponseEntity.ok()
                .body(service.forgotPassword(auth));
    }

    /**
     * User authentication method, when valid the information provides an access token.
     *
     * @param auth  credentials for authentication, username and password for access registration.
     * @return a response with the token value
     **/
    @Override
    public ResponseEntity<ForgotResponseVO> updatePassword(UpdatePassRequestBusinessVO auth) {
        log.info(LogsConst.ENDPOINT_INFO, EndpointsConst.AUTH_UPDATE_PASSWORD);
        log.debug(LogsConst.ENDPOINT_DEBUG, auth, "null");
        return ResponseEntity.ok()
                .body(service.updatePassword(auth));
    }

}
