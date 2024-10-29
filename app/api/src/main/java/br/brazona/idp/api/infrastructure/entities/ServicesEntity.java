package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

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

    public ServicesEntity() {
    }

    public ServicesEntity(Long id, String name, String sigla) {
        this.id = id;
        this.name = name;
        this.sigla = sigla;
    }
}
