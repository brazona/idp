package br.brazona.idp.api.domain.views.keycloak;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
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
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * */
    public AuthRequestKeycloakVO(){

    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param clientId jwt token
     * @param grantType jwt token
     * @param username jwt token
     * @param password jwt token
     * @param clientSecret jwt token
     * 
     * */
    public AuthRequestKeycloakVO(String clientId, String grantType, String username, String password, String clientSecret) {
        this.clientId = clientId;
        this.grantType = grantType;
        this.username = username;
        this.password = password;
        this.clientSecret = clientSecret;
    }
}
