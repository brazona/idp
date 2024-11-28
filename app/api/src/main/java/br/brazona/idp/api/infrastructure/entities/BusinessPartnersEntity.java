package br.brazona.idp.api.infrastructure.entities;

import br.brazona.idp.api.domain.enuns.GenderEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
* 
* Class Entity Business Partners
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name = "business_partners")
@Getter
@Component
public class BusinessPartnersEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String full_name;
    private String document;
    private GenderEnum genderEnum;
    private Integer user_id;
    /**
     *
     * Method constructor class.
     *
     **/
    public BusinessPartnersEntity() {
    }
}
