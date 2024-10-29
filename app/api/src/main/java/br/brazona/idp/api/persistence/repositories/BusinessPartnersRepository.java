package br.brazona.idp.api.persistence.repositories;

import br.brazona.idp.api.persistence.entities.BusinessPartnersEntity;
import org.springframework.data.repository.CrudRepository;

public interface BusinessPartnersRepository extends CrudRepository<BusinessPartnersEntity, Long> {
}
