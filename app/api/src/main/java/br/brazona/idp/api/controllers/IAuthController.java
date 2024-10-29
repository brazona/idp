package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.dtos.business.TokenDTO;
import br.brazona.idp.api.core.dtos.business.UserDTO;
import org.apache.hc.client5.http.auth.BearerToken;
import org.apache.hc.client5.http.impl.auth.BearerScheme;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/auth")
public interface IAuthController {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TokenDTO> signin(
            @RequestBody UserDTO user);

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TokenDTO> signup(@RequestHeader("service_id") String service_id,
                                    @RequestHeader("bp_id") String bp_id,
                                    @RequestBody UserDTO user);

    @PostMapping(value = "/authorization", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> authorization(@RequestHeader("Authorization") String authorization);
}
