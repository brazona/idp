package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;


/**
* 
* Class entity Users.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Entity
@Table(name = "users")
@Getter
@Component
public class UsersEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    /**
     *
     * method constructor.
     *
     **/
    public UsersEntity() {
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
    public UsersEntity(String name, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                       Boolean isCredentialsNonExpired, Boolean isEnabled ) {
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
     * @return String, name that user.
     * 
     **/
    public String getUsername() {
        return this.email;
    }
}
