package br.brazona.idp.api.application.interfaces;

import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* 
* Class that provides endpoint for user authentication and authorization.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/


@RequestMapping(value = "/v1/auth")
public interface IAuthController {

    /**
     * 
     * User authentication method, when valid the information provides an access token.
     * 
     * @param auth credentials for authentication, username and password for access registration.
     * @return a response with the token value
     * 
     **/
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthResponseBusinessVO> authentication(
            @RequestBody AuthRequestBusinessVO auth);
}
