package br.brazona.idp.api.core.dtos.business;

import br.brazona.idp.api.core.dtos.keycloak.TokenRequestDTO;
import br.brazona.idp.api.core.dtos.keycloak.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthDTO {
    @Value("${keycloak.client.id}")
    private String CLIENT_ID;
    @Value("${keycloak.grant}")
    private String GRANT_TYPE;

    public TokenRequestDTO request(LoginRequestModel loginReqApp){
        return new TokenRequestDTO(
                CLIENT_ID, GRANT_TYPE, loginReqApp.getUsername(),loginReqApp.getPassword(), "ss");
    }
    public LoginResponseModel response(TokenResponseDTO loginResApp){
        return new LoginResponseModel(loginResApp.getAccess_token());
    }
}
