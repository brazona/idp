package br.brazona.idp.api.application.interfaces;

import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/auth")
public interface IAuthController {

    /**
     * @author Brazona Tech
     * @param auth credentials for authentication, username and password for access registration.
     * @return ResponseEntity<AuthResponseBusinessVO> Access token, used to validate access to application resources through authorization.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthResponseBusinessVO> authentication(
            @RequestBody AuthRequestBusinessVO auth);
}
