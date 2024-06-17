package br.brazona.idp.api.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginKeycloakRequestModel {

    @JsonProperty(value = "client_id", required = true)
    private String clientId;
    @JsonProperty(value = "grant_type", required = true)
    private String grantType;
    @JsonProperty(value = "username", required = true)
    private String username;
    @JsonProperty(value = "password", required = true)
    private String password;
}
