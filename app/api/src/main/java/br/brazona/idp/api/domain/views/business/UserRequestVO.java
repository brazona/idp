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
public class UserRequestVO {

    private Long id;
    private String username;
    private String password;
    /**
     *
     * Method constructor class.
     *
     **/
    public UserRequestVO() {
    }
}
