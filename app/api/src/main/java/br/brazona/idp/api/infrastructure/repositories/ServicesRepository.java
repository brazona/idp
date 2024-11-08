package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.ServicesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends CrudRepository<ServicesEntity, Long> {
}
