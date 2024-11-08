package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.OwnersEntity;
import org.springframework.data.repository.CrudRepository;

public interface OwnersRepository extends CrudRepository<OwnersEntity, Long> {
}
