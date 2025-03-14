package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.email =?1")
    UsersEntity findByUsername(String username);
}
