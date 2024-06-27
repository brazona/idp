package br.brazona.idp.api.persistence.repositories;

import br.brazona.idp.api.persistence.entities.RolesEntity;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<RolesEntity, Long> {
}
