package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import br.brazona.idp.api.domain.views.keycloak.AuthRequestKeycloakVO;
import br.brazona.idp.api.domain.views.keycloak.AuthResponseKeycloakVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthDTO {

    public AuthRequestBusinessVO requestBusiness(AuthRequestKeycloakVO vo){
        return new AuthRequestBusinessVO();
    }
    public AuthRequestKeycloakVO requestKeycloak(AuthRequestBusinessVO vo){
        return new AuthRequestKeycloakVO();
    }
    public AuthResponseBusinessVO responseBusiness(AuthResponseKeycloakVO vo){
        return new AuthResponseBusinessVO();
    }
    public AuthResponseKeycloakVO responseKeycloak(AuthResponseBusinessVO vo){
        return new AuthResponseKeycloakVO();
    }
}
