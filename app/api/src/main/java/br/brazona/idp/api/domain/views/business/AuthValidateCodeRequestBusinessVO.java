package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms code the recovery access data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Component
@Getter
public class AuthValidateCodeRequestBusinessVO extends AbstractVO {
    private String username;
    private String code;


    /**
     *
     * Method that provides the object with Recovery data.
     *
     *
     **/

    public AuthValidateCodeRequestBusinessVO() {
    }

    public AuthValidateCodeRequestBusinessVO(String username, String code) {
        this.username = username;
        this.code = code;
    }
}
