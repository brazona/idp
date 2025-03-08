package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.dto.UserDTO;
import br.brazona.idp.api.domain.exceptions.UserNotFoundException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.views.business.AuthUpdateRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.UpdatePassRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.domain.views.business.UserRequestVO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Autowired
    private ExceptionUtil exceptionUtil;
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
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param username Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public UserDetailsVO getUserByUsername(String username) {
        return userDTO.toVO(usersRepository.findByUsername(username));
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param username Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public UserRequestVO getUserVOByUsername(String username) {
        UsersEntity usersEntity = usersRepository.findByUsername(username);
        if (usersEntity == null)
            throw new UserNotFoundException(exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        return userDTO.toUserVO(usersEntity);
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param userId Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public UserRequestVO getUserVOByUserId(Long userId) {
        Optional<UsersEntity> usersEntity = usersRepository.findById(userId);
        if (usersEntity.isEmpty())
            throw new UserNotFoundException(exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        return userDTO.toUserVO(usersEntity.get());
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param id Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public Boolean getUsernameByUpdate(Long id) {
       return getUserVOByUserId(id).getIsUpdatePassword();
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param authUpdateRequestBusinessVO Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public UserRequestVO updatePassworUser(UpdatePassRequestBusinessVO authUpdateRequestBusinessVO) {
        UserRequestVO userRequestVO = userDTO.toUserVO(usersRepository.findByUsername(authUpdateRequestBusinessVO.getUsername()));
        return userDTO.updateToEntity(authUpdateRequestBusinessVO, userRequestVO);
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param userRequestVO Object of the User class, with the application's authentication values.
     * @return UserDetailsVO, object class User Details
     **/
    public Boolean createOrUpdate(UserRequestVO userRequestVO) {
        try{
            usersRepository.save(userDTO.toEntity(userRequestVO));
            return true;
        }catch (Exception e){
            log.error(e.getLocalizedMessage());

        }
        return false;
    }
}
