package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.AccessDeniedException;
import br.brazona.idp.api.domain.exceptions.BadRequestException;
import br.brazona.idp.api.domain.exceptions.NotFoundException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.AuthUpdateRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.UpdatePassRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.domain.views.business.UserRequestVO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Component
@Slf4j
public class UserDTO {
    @Autowired
    private ExceptionUtil exceptionUtil;
    @Autowired
    private JwtUtils jwtUtils;
    /**
     *
     * Method constructor class.
     *
     **/
    public UserDTO() {
    }

    /**
     *
     * Method that provides the object with Users data.
     *
     * @param vo Instance of the UserRequestVO class, with value referring to the user.
     * @return object entity of class UsersEntity
     *
     **/
    public UsersEntity toEntity(UserRequestVO vo) {
        // public UsersEntity(Long id, String name, String email, String password) {
        return new UsersEntity(
                vo.getId(),
                vo.getName(),
                vo.getUsername(),
                vo.getPassword(),
                vo.getIsAccountNonExpired(),
                vo.getIsCredentialsNonExpired(),
                vo.getIsAccountNonLocked(),
                vo.getIsEnabled(),
                vo.getIsUpdatePassword()
        );
    }
    /**
     *
     * Method that provides the object with Users data.
     *
     * @param vo Instance of the UserRequestVO class, with value referring to the user.
     * @return object entity of class UsersEntity
     *
     **/
    public UserRequestVO updateToEntity(UpdatePassRequestBusinessVO vo, UserRequestVO userRequestVO) {
        if (!vo.getPasswordNew().equals(vo.getPasswordRepeat()))
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password_new_repeat"));
        else if (!jwtUtils.doPasswordsMatch(vo.getPassword(), userRequestVO.getPassword()))
            throw new BadRequestException(
               exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password_old"));
        userRequestVO.setPassword(vo.getPasswordNew());
        return userRequestVO;
    }
    /**
     *
     * Method that provides the object with Users data.
     *
     * @param entity Instance of the UsersEntity class, with value referring to the user.
     * @return object view object of class UserDetailsVO
     *
     **/
    public UserDetailsVO toVO(UsersEntity entity) {
        validateEntity(entity);
        return new UserDetailsVO(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getIsAccountNonExpired(),
                entity.getIsCredentialsNonExpired(),
                entity.getIsAccountNonLocked(),
                entity.getIsEnabled()
        );
    }
    /**
     *
     * Method that provides the object with Users data.
     *
     * @param entity Instance of the UsersEntity class, with value referring to the user.
     * @return object view object of class UserDetailsVO
     *
     **/
    public UserRequestVO toUserVO(UsersEntity entity) {
        return new UserRequestVO(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getIsAccountNonExpired(),
                entity.getIsCredentialsNonExpired(),
                entity.getIsAccountNonLocked(),
                entity.getIsEnabled(),
                entity.getIsUpdatePassword()

        );
    }
    /**
     * 
     * Method validates values of UsersEntity class properties.
     * 
     * @param entity Instance of the user class, with value referring to the user.
     * @exception NotFoundException user not found.
     * @exception BadRequestException field not present.
     *
     **/
    private void validateEntity(UsersEntity entity) {
        if (entity == null) {
            throw new NotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (entity.getId() == null || entity.getId() == 0) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "id"));
        } else if (entity.getUsername() == null || entity.getUsername().isEmpty()) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "username"));
        } else if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password"));
        } else if (entity.getIsAccountNonExpired() == null) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "isAccountNonExpired"));
        } else if (entity.getIsCredentialsNonExpired() == null) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "isCredentialsNonExpired"));
        } else if (entity.getIsAccountNonLocked() == null) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "isAccountNonLocked"));
        } else if (entity.getIsEnabled() == null) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "isEnabled"));
        }
    }

}
