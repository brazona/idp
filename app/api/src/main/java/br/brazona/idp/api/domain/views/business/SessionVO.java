package br.brazona.idp.api.domain.views.business;

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
public class SessionVO {
    private Long id;
    private Long user_id;
    private String jwt_token;
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;
    private String username;
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     **/
    public SessionVO() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param user_id Object of the User class, with the application's authentication values.
     * @param jwt_token Object of the User class, with the application's authentication values.
     * @param access_token Object of the User class, with the application's authentication values.
     * @param expires_in Object of the User class, with the application's authentication values.
     * @param refresh_expires_in Object of the User class, with the application's authentication values.
     * @param refresh_token Object of the User class, with the application's authentication values.
     * 
     **/
    public SessionVO(Long id, Long user_id, String jwt_token, String access_token,
                     String expires_in, String refresh_expires_in, String refresh_token) {
        this.id = id;
        this.user_id = user_id;
        this.jwt_token = jwt_token;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_expires_in = refresh_expires_in;
        this.refresh_token = refresh_token;
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param user_id Object of the User class, with the application's authentication values.
     * @param access_token Object of the User class, with the application's authentication values.
     * 
     **/
    public SessionVO(Long user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param username Object of the User class, with the application's authentication values.
     * @param access_token Object of the User class, with the application's authentication values.
     * 
     **/
    public SessionVO(String username, String access_token) {
        this.username = username;
        this.access_token = access_token;
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param access_token Object of the User class, with the application's authentication values.
     * @param user_id Object of the User class, with the application's authentication values.
     * 
     **/
    public SessionVO(Long id, Long user_id, String access_token) {
        this.id = id;
        this.user_id = user_id;
        this.access_token = access_token;
    }
}
