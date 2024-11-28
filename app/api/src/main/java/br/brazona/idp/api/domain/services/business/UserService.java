package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.UserDTO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Slf4j
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDTO userDTO;
    /**
     *
     * method constructor.
     *
     **/
    public UserService() {
    }

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param username Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public UserDetailsVO getByUsername(String username) {
        return userDTO.toVO(usersRepository.findByUsername(username));
    }
}
