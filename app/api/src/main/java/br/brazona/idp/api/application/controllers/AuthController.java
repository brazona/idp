package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.application.interfaces.IAuthController;
import br.brazona.idp.api.domain.dto.business.TokenDTO;
import br.brazona.idp.api.domain.dto.business.UserDTO;
import br.brazona.idp.api.domain.dto.business.UserDetailsImplDTO;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.services.business.AuthService;
import br.brazona.idp.api.domain.services.business.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
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

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override
    public ResponseEntity<TokenDTO> signin(UserDTO user) {
        TokenDTO tokenDTO = service.signIn(user);
        return ResponseEntity.ok()
                .header("Authorization", tokenDTO.getToken())
                .body(tokenDTO);
    }

    @Override
    public ResponseEntity<TokenDTO> signup(
            String service_id, String bp_id, UserDTO user) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> authorization(String authorization) {

        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ") || !jwtUtils.validateJwtToken(authorization.substring(7))) {
            return ResponseEntity.ok(false);
        }
        String jwt = authorization.substring(7);

        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        UserDetailsImplDTO userDetails = userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(
                service.authorization(userDetails.getId())
        );
    }
}
