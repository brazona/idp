package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.business.UserDTO;
import br.brazona.idp.api.domain.exceptions.UserNotFoundException;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersEntity usersEntity;
    @Autowired
    private UserDTO userDTO;

    private final static String SERVICE_LOG = "Service started UserService: {}";
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public void create(UserDTO userDTO){
        logger.info(SERVICE_LOG, "create");
        usersEntity.setName(userDTO.getUsername());
        usersRepository.save(usersEntity);
    }

    public UserDTO getById(Long id){
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);
        if (usersEntity.isEmpty()){
            throw new UserNotFoundException();
        }
        return usersEntity.map(entity -> new UserDTO(entity.getId() ,entity.getName())).orElse(null);
    }

}
