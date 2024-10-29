package br.brazona.idp.api.core.dtos;

import br.brazona.idp.api.persistence.entities.UsersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserDetailsDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Long id, String username, String password, String email,
                   Boolean isAccountNonExpired, Boolean isAccountNonLocked, Boolean isCredentialsNonExpired,
                   Boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
    public UserDetailsDTO(UsersEntity userDetails){

//        this.id = id;
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
        this.email = userDetails.getUsername();
        this.isAccountNonExpired = userDetails.getIsAccountNonExpired();
        this.isAccountNonLocked = userDetails.getIsAccountNonLocked();
        this.isCredentialsNonExpired = userDetails.getIsCredentialsNonExpired();
        this.isEnabled = userDetails.getIsEnabled();
    }

}

