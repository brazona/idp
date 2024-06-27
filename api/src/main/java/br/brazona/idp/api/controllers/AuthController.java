package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.config.security.JwtUtils;
import br.brazona.idp.api.core.dtos.TokenDTO;
import br.brazona.idp.api.core.dtos.UserDTO;
import br.brazona.idp.api.core.dtos.UserDetailsImplDTO;
import br.brazona.idp.api.services.business.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        UserDetailsImplDTO userDetails = (UserDetailsImplDTO) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new TokenDTO(jwt));
    }

    @Override
    public ResponseEntity<TokenDTO> signup(UserDTO user) {
        return null;
    }
}
