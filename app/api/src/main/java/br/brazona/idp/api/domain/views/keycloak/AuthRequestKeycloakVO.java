package br.brazona.idp.api.domain.views.keycloak;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Setter
public class AuthRequestKeycloakVO {


    @JsonProperty(value = "client_id", required = true)
    private String clientId;
    @JsonProperty(value = "client_secret", required = true)
    private String clientSecret;
    @JsonProperty(value = "grant_type", required = true)
    private String grantType;
    @JsonProperty(value = "username", required = true)
    private String username;
    @JsonProperty(value = "password", required = true)
    private String password;

    public AuthRequestKeycloakVO(){

    }
    public AuthRequestKeycloakVO(String clientId, String grantType, String username, String password, String clientSecret) {
        this.clientId = clientId;
        this.grantType = grantType;
        this.username = username;
        this.password = password;
        this.clientSecret = clientSecret;
    }
}
