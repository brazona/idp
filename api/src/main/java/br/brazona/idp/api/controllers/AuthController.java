package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.dtos.TokenDTO;
import br.brazona.idp.api.core.dtos.UserDTO;
import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import br.brazona.idp.api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {
    @Autowired
    private AuthService service;

    @Override
    public ResponseEntity<TokenDTO> token(UserDTO user) throws Throwable {
        return ResponseEntity.ok(service.getToken(user));
    }

    @Override
    public ResponseEntity<String> tokenValidated(String token, String userId) throws Throwable {
        service.isTokenValidated(token, userId);
        return ResponseEntity.ok("valido");
    }

}
