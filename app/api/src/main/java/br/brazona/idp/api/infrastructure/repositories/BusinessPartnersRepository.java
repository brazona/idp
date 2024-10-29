package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.BusinessPartnersEntity;
import org.springframework.data.repository.CrudRepository;

public interface BusinessPartnersRepository extends CrudRepository<BusinessPartnersEntity, Long> {
}
