package br.brazona.idp.api.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "business_partners")
@Getter
@Setter
@Component
public class BusinessPartnersEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="bp_sequence")
    @SequenceGenerator(name="bp_sequence", sequenceName="bp_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private  String cnpj;
    @Column(unique = true)
    private String name_social;
    @Column(unique = true)
    private String name;
}
