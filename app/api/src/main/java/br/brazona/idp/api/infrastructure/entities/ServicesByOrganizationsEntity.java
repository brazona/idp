package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "services_by_organizations")
@Getter
@Component
public class ServicesByOrganizationsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private Integer organization_id;
    private Integer service_id;
    private Integer business_partner_id;
    private Integer role_id;

    public ServicesByOrganizationsEntity() {
    }

    public ServicesByOrganizationsEntity(Long id, String name, Integer organization_id,
                                         Integer service_id, Integer business_partner_id,
                                         Integer role_id) {
        this.id = id;
        this.name = name;
        this.organization_id = organization_id;
        this.service_id = service_id;
        this.business_partner_id = business_partner_id;
        this.role_id = role_id;
    }
}
