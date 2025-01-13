package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.RolesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
*
* Interface class for connecting to table database [ RolesRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public interface RolesRepository extends CrudRepository<RolesEntity, Long> {
}
