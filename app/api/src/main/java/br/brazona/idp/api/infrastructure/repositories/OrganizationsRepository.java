package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.OrganizationsEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationsRepository extends CrudRepository<OrganizationsEntity, Long> {
}
