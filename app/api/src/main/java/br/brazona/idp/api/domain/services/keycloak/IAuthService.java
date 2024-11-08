package br.brazona.idp.api.domain.services.keycloak;

import br.brazona.idp.api.domain.views.keycloak.AuthResponseKeycloakVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "${keycloak.name}", url = "${keycloak.url}")
public interface IAuthService {

    @PostMapping(value = "/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<AuthResponseKeycloakVO> authentication(@RequestBody Map<String, ?> form);


}
