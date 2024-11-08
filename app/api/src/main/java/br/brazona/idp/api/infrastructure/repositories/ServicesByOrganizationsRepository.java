package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.ServicesByOrganizationsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServicesByOrganizationsRepository extends CrudRepository<ServicesByOrganizationsEntity, Long> {
}
