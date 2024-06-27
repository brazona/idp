package br.brazona.idp.api.persistence.repositories;

import br.brazona.idp.api.persistence.entities.ServicesProviderEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServicesProviderRepository extends CrudRepository<ServicesProviderEntity, Long> {
}
