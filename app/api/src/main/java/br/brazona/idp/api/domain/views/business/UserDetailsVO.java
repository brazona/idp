package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.util.Collection;

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
public class UserDetailsVO implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * */
    public UserDetailsVO() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     *
     * @param id Object of the User class, with the application's authentication values.
     * @param name Object of the User class, with the application's authentication values.
     * @param username Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     * @param isAccountNonExpired Object of the User class, with the application's authentication values.
     * @param isAccountNonLocked Object of the User class, with the application's authentication values.
     * @param isCredentialsNonExpired Object of the User class, with the application's authentication values.
     * @param isEnabled Object of the User class, with the application's authentication values.
     * 
     **/
    public UserDetailsVO(Long id, String name, String username, String password,
                         Boolean isAccountNonExpired, Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
