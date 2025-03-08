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
* Class Entity Addresses.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Entity
@Table(name = "addresses")
@Getter
@Component
public class AddressesEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String street;
    private String neighborhood;
    private String number;
    private String complement;
    private String state;
    private String zipcode;
    private String country;
    private Long business_partner_id;

    /**
     *
     * Class constructor.
     *
     **/
    public AddressesEntity() {
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @param street Object of the User class, with the application's authentication values.
     * @param neighborhood Object of the User class, with the application's authentication values.
     * @param number Object of the User class, with the application's authentication values.
     * @param complement Object of the User class, with the application's authentication values.
     * @param state Object of the User class, with the application's authentication values.
     * @param zipcode Object of the User class, with the application's authentication values.
     * @param country Object of the User class, with the application's authentication values.
     * @param business_partner_id Object of the User class, with the application's authentication values.
     * 
     **/
    public AddressesEntity(Long id, String street, String neighborhood,
                           String number, String complement, String state,
                           String zipcode, String country, Long business_partner_id) {
        this.id = id;
        this.street = street;
        this.neighborhood = neighborhood;
        this.number = number;
        this.complement = complement;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.business_partner_id = business_partner_id;
    }
}
