package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.UserPermissionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserPermissionsRepository extends CrudRepository<UserPermissionsEntity, Long> {
}
