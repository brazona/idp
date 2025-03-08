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
@Component
@Getter
public class AuthUpdateRequestBusinessVO extends AbstractVO {
    private String username;
    private String password;
    private String password_new;
    private String password_new_repeat;

    /**
     *
     * Method that provides the object with authentication data.
     *
     *
     **/

    public AuthUpdateRequestBusinessVO() {
    }

    public AuthUpdateRequestBusinessVO(String username, String password, String password_new, String password_new_repeat) {
        this.username = username;
        this.password = password;
        this.password_new = password_new;
        this.password_new_repeat = password_new_repeat;
    }
}
