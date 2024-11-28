package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.OwnersEntity;
import org.springframework.data.repository.CrudRepository;

/**
*
* Interface class for connecting to table database [ OwnersRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

public interface OwnersRepository extends CrudRepository<OwnersEntity, Long> {
}
