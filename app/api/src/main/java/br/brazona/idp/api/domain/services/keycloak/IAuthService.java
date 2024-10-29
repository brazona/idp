package br.brazona.idp.api.domain.services.keycloak;

import br.brazona.idp.api.domain.dto.keycloak.IntrospectResponseDTO;
import br.brazona.idp.api.domain.dto.keycloak.TokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "${keycloak.name}", url = "${keycloak.url}")
public interface IAuthService {

    @PostMapping(value = "/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<TokenResponseDTO> signIn(@RequestBody Map<String, ?> form);

    @PostMapping(value = "/protocol/openid-connect/token/introspect", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<IntrospectResponseDTO> authorizationIntrospect(@RequestBody Map<String, ?> form);

}
