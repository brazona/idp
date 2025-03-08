package br.brazona.idp.api.domain.services.keycloak;

import br.brazona.idp.api.domain.views.keycloak.AuthResponseKeycloakVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

/**
* 
* Class Interface Feign to Service Keycloak.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@FeignClient(name = "${keycloak.name}", url = "${keycloak.url}")
public interface IAuthService {
     /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param form Object of the User class, with the application's authentication values.
     * @return AuthResponseKeycloakVO, view object token
     *
     **/
    @PostMapping(value = "/protocol/openid-connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<AuthResponseKeycloakVO> authentication(@RequestBody Map<String, ?> form);


}
