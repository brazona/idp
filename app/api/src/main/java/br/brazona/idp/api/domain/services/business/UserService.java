/**
 * @author Brazona Tech
 **/
package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.UserDTO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDTO userDTO;
    /**
     * @param username user name system identifier..
     * @return UserDetailsVO Application access token, containing authorization data for services and resources..
     **/
    public UserDetailsVO getByUsername(String username) {
        return userDTO.toVO(usersRepository.findByUsername(username));
    }
}
