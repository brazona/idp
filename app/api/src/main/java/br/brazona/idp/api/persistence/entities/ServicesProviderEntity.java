package br.brazona.idp.api.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "services_providers")
@Getter
@Setter
@Component
public class ServicesProviderEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="services_sequence")
    @SequenceGenerator(name="services_sequence", sequenceName="services_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String client_id;
    private String client_secret;

}
