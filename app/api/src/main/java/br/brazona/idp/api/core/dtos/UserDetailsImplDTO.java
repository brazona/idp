package br.brazona.idp.api.core.dtos;

import br.brazona.idp.api.persistence.entities.UsersEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImplDTO implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    public UserDetailsImplDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public static UserDetailsImplDTO build(UsersEntity user) {

        return new UserDetailsImplDTO(user.getId(), user.getUsername(), user.getPassword());
    }

    public Long getId() {
        return id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImplDTO user = (UserDetailsImplDTO) o;
        return Objects.equals(id, user.id);
    }
}
