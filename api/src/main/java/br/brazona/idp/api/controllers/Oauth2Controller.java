package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import br.brazona.idp.api.services.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/oauth2")
public class Oauth2Controller implements IOauth2Controller {
    @Autowired
    private Oauth2Service service;
    @Override
    public ResponseEntity<LoginResponseModel> token(LoginRequestModel login) {
        return ResponseEntity.ok(service.login(login));
    }
}
