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
* Class entity Owners
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name = "owners")
@Getter
@Component
public class OwnersEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String full_name;
    private String document;
    private Integer user_id;
    /**
     *
     * Class constructor.
     *
     **/
    public OwnersEntity() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param full_name Object of the User class, with the application's authentication values.
     * @param document Object of the User class, with the application's authentication values.
     * @param user_id Object of the User class, with the application's authentication values.
     * 
     **/
    public OwnersEntity(Long id, String full_name, String document, Integer user_id) {
        this.id = id;
        this.full_name = full_name;
        this.document = document;
        this.user_id = user_id;
    }
}
