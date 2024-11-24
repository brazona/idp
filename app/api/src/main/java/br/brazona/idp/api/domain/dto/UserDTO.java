/**
 * @author Brazona Tech
 **/
package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.BadRequestException;
import br.brazona.idp.api.domain.exceptions.NotFoundException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.domain.views.business.UserRequestVO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDTO {
    @Autowired
    private ExceptionUtil exceptionUtil;
    public UsersEntity toEntity(UserRequestVO vo) {
        return new UsersEntity(

        );
    }
    /**
     * @param entity Instance of the session class, with value referring to the user.
     * @return UserDetailsVO User entity, with access data to the application.
     **/
    public UserDetailsVO toVO(UsersEntity entity) {
        validateEntity(entity);
        return new UserDetailsVO(
                entity.getId(), entity.getName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getIsAccountNonExpired(),
                entity.getIsCredentialsNonExpired(),
                entity.getIsAccountNonLocked(),
                entity.getIsEnabled()
        );
    }
    /**
     * @param entity Instance of the session class, with value referring to the user.
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
