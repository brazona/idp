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
public class UpdatePassRequestBusinessVO extends AbstractVO {



    private String username;
    private String password;
    private String passwordNew;
    private String passwordRepeat;

    /**
     *
     * Method that provides the object with authentication data.
     *
     *
     **/

    public UpdatePassRequestBusinessVO() {
    }

    public UpdatePassRequestBusinessVO(String username, String password, String passwordNew, String passwordRepeat) {
        this.username = username;
        this.password = password;
        this.passwordNew = passwordNew;
        this.passwordRepeat = passwordRepeat;
    }
}
