package br.brazona.idp.api.services;

import br.brazona.idp.api.core.models.LoginKeycloakRequestModel;
import br.brazona.idp.api.core.models.LoginKeycloakResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Component
@FeignClient(name = "${keycloak.name}", url = "${keycloak.url}")
public interface IAuthService {

    @PostMapping(value = "/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<String> signIn(@RequestBody Map<String, ?> form);

    @PostMapping(value = "/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<String> validatedToken(@RequestBody Map<String, ?> form);
}
