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
* Class Entity Organization.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name = "organizations")
@Getter
@Component
public class OrganizationsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String fantasy_name;
    private String document;
    private String corporate_reason;
    private Integer owner_id;
    /**
     *
     * Class constructor.
     *
     **/
    public OrganizationsEntity() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param fantasy_name Object of the User class, with the application's authentication values.
     * @param document Object of the User class, with the application's authentication values.
     * @param corporate_reason Object of the User class, with the application's authentication values.
     * @param owner_id Object of the User class, with the application's authentication values.
     * 
     **/
    public OrganizationsEntity(Long id, String fantasy_name,
                               String document, String corporate_reason, Integer owner_id) {
        this.id = id;
        this.fantasy_name = fantasy_name;
        this.document = document;
        this.corporate_reason = corporate_reason;
        this.owner_id = owner_id;
    }
}
