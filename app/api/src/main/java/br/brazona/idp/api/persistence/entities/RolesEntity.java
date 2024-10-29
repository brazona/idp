package br.brazona.idp.api.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Entity
@Table(name="roles")
@Getter
@Setter
public class RolesEntity implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="roles_sequence")
    @SequenceGenerator(name="roles_sequence", sequenceName="roles_seq", allocationSize = 1)
    private Long id;
    private String name;

    public RolesEntity() {
    }

    public RolesEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
