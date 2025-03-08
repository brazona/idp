package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
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

@Getter
@Component
public class AuthResponseBusinessVO {

    private String token;
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * 
     **/
    public AuthResponseBusinessVO() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param token Object of the User class, with the application's authentication values.
     * 
     **/
    public AuthResponseBusinessVO(String token) {
        this.token = token;
    }
}
