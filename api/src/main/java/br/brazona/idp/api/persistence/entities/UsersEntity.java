package br.brazona.idp.api.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@Component
public class UsersEntity implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_sequence")
    @SequenceGenerator(name="users_sequence", sequenceName="users_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_relation_permission",  // DEFINE NOME DA TABELA
            joinColumns = @JoinColumn(name="user_id"), // DEFINE O NOME DA COLUNA REFERENTE A PRIMARY KEY DESTA TABELA
            inverseJoinColumns = @JoinColumn(name="permission_id") // DEFINE O NOME DA COLUNA REFERENTE A PRIMARY KEY DA TABELA RELACIONAL
    )
    private Set<UserPermissionsEntity> roles = new HashSet<>();

    public UsersEntity() {
    }

    public UsersEntity(Long id, String name, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                       Boolean isCredentialsNonExpired, Boolean isEnabled,
                       Set<UserPermissionsEntity> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public GrantedAuthority getRole(Long bpId, Long serviceId){

        for (UserPermissionsEntity i : roles) {
            if (bpId == i.getBp().getId() && serviceId == i.getService().getId()){
                return i.getRole();
            }
        }
        return null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonExpired;
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
