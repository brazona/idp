package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
*
* Interface class for connecting to table database [ UsersRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

public interface UsersRepository extends CrudRepository<UsersEntity, Long> {
    /**
     *
     * Method that queries the user record by name.
     *
     * @param username, name that user.
     * @return UsersEntity, Class Entity Users.
     *
     **/
    @Query("SELECT u FROM UsersEntity u WHERE u.email =?1")
    UsersEntity findByUsername(String username);
}
