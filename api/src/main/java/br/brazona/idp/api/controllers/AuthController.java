package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.config.security.JwtUtils;
import br.brazona.idp.api.core.dtos.business.TokenDTO;
import br.brazona.idp.api.core.dtos.business.UserDTO;
import br.brazona.idp.api.services.business.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

    @Autowired(required = false)
    AuthenticationManager authenticationManager;

    @Autowired(required = false)
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private AuthService service;


    @Override
    public ResponseEntity<TokenDTO> signin(UserDTO user) {
        TokenDTO tokenDTO = service.signIn(user);
        return ResponseEntity.ok()
                .header("Authorization", tokenDTO.getToken())
                .body(tokenDTO);
    }

    @Override
    public ResponseEntity<TokenDTO> signup(String service_id, String bp_id, UserDTO user) {
        return null;
    }
}
