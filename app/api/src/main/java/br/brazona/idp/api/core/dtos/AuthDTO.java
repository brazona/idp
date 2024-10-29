package br.brazona.idp.api.core.dtos;

import br.brazona.idp.api.core.models.LoginKeycloakRequestModel;
import br.brazona.idp.api.core.models.LoginKeycloakResponseModel;
import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthDTO {
    @Value("${keycloak.client.id}")
    private String CLIENT_ID;
    @Value("${keycloak.grant}")
    private String GRANT_TYPE;

    public LoginKeycloakRequestModel request(LoginRequestModel loginReqApp){
        return new LoginKeycloakRequestModel(
                CLIENT_ID, GRANT_TYPE, loginReqApp.getUsername(),loginReqApp.getPassword());
    }
    public LoginResponseModel response(LoginKeycloakResponseModel loginResApp){
        return new LoginResponseModel(loginResApp.getAccess_token());
    }
}
