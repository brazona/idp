package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, Long> {

    @Query("SELECT u FROM SessionEntity u WHERE u.user_id =?1")
    SessionEntity findByUserId(Long user_id);
}
