package br.brazona.idp.api.core.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class KeycloakIntrospectRequestDTO {
    @JsonProperty(value = "client_id", required = true)
    private String clientId;
    @JsonProperty(value = "client_secret", required = true)
    private String clientSecret;
    @JsonProperty(value = "token", required = true)
    private String token;

    public KeycloakIntrospectRequestDTO(){
        
    }
    public KeycloakIntrospectRequestDTO(String clientId, String clientSecret, String token) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.token = token;
    }
}
