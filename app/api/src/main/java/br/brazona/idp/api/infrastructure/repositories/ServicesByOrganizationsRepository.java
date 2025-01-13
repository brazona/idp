package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.ServicesByOrganizationsEntity;
import org.springframework.data.repository.CrudRepository;

/**
*
* Interface class for connecting to table database [ ServicesByOrganizationsRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

public interface ServicesByOrganizationsRepository extends CrudRepository<ServicesByOrganizationsEntity, Long> {
}
