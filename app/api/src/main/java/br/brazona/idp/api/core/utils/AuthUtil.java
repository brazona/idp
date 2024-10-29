package br.brazona.idp.api.core.utils;

import br.brazona.idp.api.core.dtos.business.LoginRequestDTO;
import br.brazona.idp.api.core.dtos.business.LoginResponseDTO;
import br.brazona.idp.api.core.dtos.keycloak.IntrospectRequestDTO;
import br.brazona.idp.api.core.dtos.keycloak.TokenRequestDTO;
import br.brazona.idp.api.core.dtos.keycloak.TokenResponseDTO;
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

    public TokenRequestDTO request(LoginRequestDTO loginReqApp){
        return new TokenRequestDTO(
                CLIENT_ID, GRANT_TYPE, loginReqApp.getUsername(),loginReqApp.getPassword(), CLIENT_SECRET);
    }
    public LoginResponseDTO response(TokenResponseDTO loginResApp){
        return new LoginResponseDTO(loginResApp.getAccess_token());
    }
    public IntrospectRequestDTO isValidTokenRequest(String token){
        return new IntrospectRequestDTO(CLIENT_ID, CLIENT_SECRET, token);
    }

}
