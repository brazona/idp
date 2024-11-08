package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

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

    public OrganizationsEntity() {
    }

    public OrganizationsEntity(Long id, String fantasy_name,
                               String document, String corporate_reason, Integer owner_id) {
        this.id = id;
        this.fantasy_name = fantasy_name;
        this.document = document;
        this.corporate_reason = corporate_reason;
        this.owner_id = owner_id;
    }
}
