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
* Class entity Services.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name = "services")
@Getter
@Component
public class ServicesEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private String sigla;

    /**
     *
     * Class constructor.
     *
     **/
    public ServicesEntity() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * @param id identificador
     * @param name identificador
     * @param sigla identificador
     * 
     **/
    public ServicesEntity(Long id, String name, String sigla) {
        this.id = id;
        this.name = name;
        this.sigla = sigla;
    }
}
