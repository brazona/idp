package br.brazona.idp.api.domain.views.business;

import jakarta.persistence.Id;
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
public class UserRequestVO {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    private Boolean isUpdatePassword;
    /**
     *
     * method constructor.
     *
     **/
    public UserRequestVO() {
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param name Object of the User class, with the application's authentication values.
     * @param email Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     * @param isAccountNonExpired Object of the User class, with the application's authentication values.
     * @param isAccountNonLocked Object of the User class, with the application's authentication values.
     * @param isCredentialsNonExpired Object of the User class, with the application's authentication values.
     * @param isEnabled Object of the User class, with the application's authentication values.
     *
     **/
    public UserRequestVO(Long id, String name, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                       Boolean isCredentialsNonExpired, Boolean isEnabled ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param name Object of the User class, with the application's authentication values.
     * @param email Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     * @param isAccountNonExpired Object of the User class, with the application's authentication values.
     * @param isAccountNonLocked Object of the User class, with the application's authentication values.
     * @param isCredentialsNonExpired Object of the User class, with the application's authentication values.
     * @param isEnabled Object of the User class, with the application's authentication values.
     *
     **/
    public UserRequestVO(Long id, String name, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                         Boolean isCredentialsNonExpired, Boolean isEnabled, Boolean isUpdatePassword ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.isUpdatePassword = isUpdatePassword;
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param id Object of the User class, with the application's authentication values.
     * @param email Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     *
     **/
    public UserRequestVO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    /**
     *
     * Method that provides the object with authentication data.
     * @return String, name that user.
     *
     **/
    public String getUsername() {
        return this.email;
    }
}
