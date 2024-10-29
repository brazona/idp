package br.brazona.idp.api.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Setter
public class LoginKeycloakRequestModel {


    @JsonProperty(value = "client_id", required = true)
    private String clientId;
    @JsonProperty(value = "grant_type", required = true)
    private String grantType;
    @JsonProperty(value = "username", required = true)
    private String username;
    @JsonProperty(value = "password", required = true)
    private String password;

    public LoginKeycloakRequestModel(){

    }
    public LoginKeycloakRequestModel(String clientId, String grantType, String username, String password) {
        this.clientId = clientId;
        this.grantType = grantType;
        this.username = username;
        this.password = password;
    }
}
