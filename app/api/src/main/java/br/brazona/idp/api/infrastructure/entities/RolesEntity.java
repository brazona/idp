package br.brazona.idp.api.infrastructure.entities;

import br.brazona.idp.api.domain.enuns.RolesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
* 
* Class entity Roles
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name="roles")
@Getter
public class RolesEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private RolesEnum name;
    /**
     *
     * Class constructor.
     *
     **/
    public RolesEntity() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param name Object of the User class, with the application's authentication values.
     * 
     **/
    public RolesEntity(Long id, RolesEnum name) {
        this.id = id;
        this.name = name;
    }

}
