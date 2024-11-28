package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.ServicesEntity;
import org.springframework.data.repository.CrudRepository;

/**
*
* Interface class for connecting to table database [ ServicesRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public interface ServicesRepository extends CrudRepository<ServicesEntity, Long> {
}
