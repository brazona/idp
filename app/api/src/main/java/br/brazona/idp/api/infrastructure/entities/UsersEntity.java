package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "users")
@Getter
@Component
public class UsersEntity implements Serializable, GenericEntity {
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

    public UsersEntity() {
    }
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

    public String getUsername() {
        return this.email;
    }
}
