package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.application.interfaces.IAuthController;
import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {


    @Override
    public ResponseEntity<AuthResponseBusinessVO> authentication(AuthRequestBusinessVO auth) {
        return null;
    }
}
