package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.OrganizationsEntity;
import org.springframework.data.repository.CrudRepository;

/**
*
* Interface class for connecting to table database [ OrganizationsRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

public interface OrganizationsRepository extends CrudRepository<OrganizationsEntity, Long> {
}
