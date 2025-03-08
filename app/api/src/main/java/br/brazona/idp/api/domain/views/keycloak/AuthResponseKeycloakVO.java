package br.brazona.idp.api.domain.views.keycloak;

import lombok.Getter;
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
@Getter
@Setter
public class AuthResponseKeycloakVO {

    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private String not_before_policy;
    private String session_state;
    private String scope;
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * */
    public AuthResponseKeycloakVO() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param accessToken jwt token
     * @param refreshExpiresIn jwt token
     * @param refreshToken jwt token
     * @param tokenType jwt token
     * @param notBeforePolicy jwt token
     * @param sessionState jwt token
     * @param scope jwt token
     * 
     * */
    public AuthResponseKeycloakVO(String accessToken, String refreshExpiresIn, String refreshToken, String tokenType, String notBeforePolicy, String sessionState, String scope) {
        this.access_token = accessToken;
        this.refresh_expires_in = refreshExpiresIn;
        this.refresh_token = refreshToken;
        this.token_type = tokenType;
        this.not_before_policy = notBeforePolicy;
        this.session_state = sessionState;
        this.scope = scope;
    }
}
