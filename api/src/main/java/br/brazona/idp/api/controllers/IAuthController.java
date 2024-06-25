package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.dtos.TokenDTO;
import br.brazona.idp.api.core.dtos.UserDTO;
import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/v1/auth")
public interface IAuthController {

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> token(@RequestBody UserDTO user) throws Throwable;

    @PostMapping(value = "/token/validated", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> tokenValidated(@RequestHeader(value = "token", required = true) String token,
                                                   @RequestHeader(value = "userId", required = true) String userId ) throws Throwable;
}
