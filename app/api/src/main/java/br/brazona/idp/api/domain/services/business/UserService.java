package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.UserDTO;
import br.brazona.idp.api.domain.exceptions.UserNotFoundException;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersEntity usersEntity;
    @Autowired
    private UserDTO userDTO;

    @Value("${keycloak.client.id}")
    private String CLIENT_ID;
    @Value("${keycloak.client.secret}")
    private String CLIENT_SECRET;
    @Value("${keycloak.grant}")
    private String GRANT_TYPE;

    private final static String SERVICE_LOG = "Service started UserService: {}";
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public void create(UserDTO userDTO){
        logger.info(SERVICE_LOG, "create");
        usersRepository.save(usersEntity);
    }

    public UserDTO getById(Long id){
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);
        if (usersEntity.isEmpty()){
            throw new UserNotFoundException();
        }
        return usersEntity.map(entity -> new UserDTO()).orElse(null);
    }
}
