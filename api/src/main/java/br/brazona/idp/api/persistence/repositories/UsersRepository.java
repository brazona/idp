package br.brazona.idp.api.persistence.repositories;

import br.brazona.idp.api.persistence.entities.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.email =?1")
    UsersEntity findByUsername(String username);
}
