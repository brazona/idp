package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

/**
* 
* Class entity Roles Grant
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RolesGrantEntity implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence")
    @SequenceGenerator(name = "roles_sequence", sequenceName = "roles_seq", allocationSize = 1)
    private Long id;
    private String name;
    /**
     *
     * Class constructor.
     *
     **/
    public RolesGrantEntity() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param name Object of the User class, with the application's authentication values.
     * 
     **/
    public RolesGrantEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
