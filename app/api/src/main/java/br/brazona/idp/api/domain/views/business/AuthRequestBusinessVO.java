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
public class AuthRequestBusinessVO extends AbstractVO {


    private Long id;
    private String username;
    private String password;

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * 
     **/

    public AuthRequestBusinessVO() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param username Object of the User class, with the application's authentication values.
     * @param id Object of the User class, with the application's authentication values.
     * 
     **/
    
    public AuthRequestBusinessVO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param username Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     * 
     **/
    
    public AuthRequestBusinessVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param id Object of the User class, with the application's authentication values.
     * @param username Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     *
     **/

    public AuthRequestBusinessVO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
