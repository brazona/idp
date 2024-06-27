package br.brazona.idp.api.persistence.repositories;

import br.brazona.idp.api.persistence.entities.UserPermissionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserPermissionsRepository extends CrudRepository<UserPermissionsEntity, Long> {
}
