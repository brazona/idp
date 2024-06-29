package br.brazona.idp.api.core.utils;

import br.brazona.idp.api.core.dtos.keycloak.IntrospectRequestDTO;
import br.brazona.idp.api.core.dtos.keycloak.TokenRequestDTO;
import br.brazona.idp.api.core.dtos.keycloak.TokenResponseDTO;
import br.brazona.idp.api.core.dtos.business.LoginRequestModel;
import br.brazona.idp.api.core.dtos.business.LoginResponseModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    @Value("${keycloak.client.id}")
    private String CLIENT_ID;
    @Value("${keycloak.client.secret}")
    private String CLIENT_SECRET;
    @Value("${keycloak.grant}")
    private String GRANT_TYPE;

    public TokenRequestDTO request(LoginRequestModel loginReqApp){
        return new TokenRequestDTO(
                CLIENT_ID, GRANT_TYPE, loginReqApp.getUsername(),loginReqApp.getPassword(), CLIENT_SECRET);
    }
    public LoginResponseModel response(TokenResponseDTO loginResApp){
        return new LoginResponseModel(loginResApp.getAccess_token());
    }
    public String generateHash(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }
    public IntrospectRequestDTO isValidTokenRequest(String token){
        return new IntrospectRequestDTO(CLIENT_ID, CLIENT_SECRET, token);
    }

}
