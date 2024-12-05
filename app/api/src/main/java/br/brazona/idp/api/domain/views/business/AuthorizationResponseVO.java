package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms ForgotResponseVO data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Getter
@Component
public class AuthorizationResponseVO {
    private Boolean is_authorized;
    private String message;

    public AuthorizationResponseVO() {
    }

    public AuthorizationResponseVO(Boolean is_authorized, String message) {
        this.is_authorized = is_authorized;
        this.message = message;
    }
}
