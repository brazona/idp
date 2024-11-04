package br.brazona.idp.api.infrastructure.repositories;

import br.brazona.idp.api.infrastructure.entities.AddressesEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressesRepository extends CrudRepository<AddressesEntity, Long> {
}
