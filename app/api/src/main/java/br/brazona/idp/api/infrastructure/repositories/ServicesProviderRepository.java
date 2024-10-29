package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.ServicesProviderEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServicesProviderRepository extends CrudRepository<ServicesProviderEntity, Long> {
}
