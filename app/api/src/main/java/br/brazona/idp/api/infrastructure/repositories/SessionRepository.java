package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
* 
* Interface class for connecting to table database [ SessionRepository ]
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

public interface SessionRepository extends CrudRepository<SessionEntity, Long> {
    /**
     *
     * Method that queries session record with user id.
     *
     * @param user_id, id that record user.
     * @return SessionEntity, Class Entity Session.
     *
     **/
    @Query("SELECT u FROM SessionEntity u WHERE u.user_id =?1")
    SessionEntity findByUserId(Long user_id);
}
