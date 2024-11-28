package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.BadRequestException;
import br.brazona.idp.api.domain.exceptions.NotFoundException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.views.business.SessionVO;
import br.brazona.idp.api.infrastructure.entities.SessionEntity;
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
public class SessionDTO {
    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     *
     * Method constructor class.
     *
     **/
    public SessionDTO() {
    }

    /**
     * 
     * Method that converts entity session object into view object
     * 
     * @param entity  credentials for authentication, username and password for access registration.
     * @return object view object of class SessionVO
     *
     **/
    public SessionVO toVO(SessionEntity entity) {
        validateEntity(entity);
        return new SessionVO(entity.getId(), entity.getUser_id(), entity.getAccess_token());
    }
    /**
     *
     * Method that converts iew object session object into entity
     * 
     * @param vo Instance of the session class, with value referring to the user session.
     * @return object entity of class SessionEntity
     *
     **/
    public SessionEntity toEntity(SessionVO vo) {
        return new SessionEntity(vo.getUser_id(), vo.getAccess_token());
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param vo Instance of the session class, with value referring to the user session.
     * @return object entity of class SessionEntity
     *
     **/
    public SessionEntity toEntityByID(SessionVO vo) {
        return new SessionEntity(vo.getId(), vo.getUser_id(), vo.getAccess_token());
    }
    /**
     * 
     * Method that validates values of SessionEntity class properties.
     * 
     * @param entity Instance of the session class, with value referring to the user session.
     * @exception NotFoundException session not found.
     * @exception BadRequestException field not present.
     *
     **/
    private void validateEntity(SessionEntity entity) {
        if (entity == null) {
            throw new NotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "session"));
        } else if (entity.getId() == null || entity.getId() == 0) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "id"));
        } else if (entity.getUser_id() == null || entity.getUser_id() == 0) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "user_id"));
        } else if (entity.getAccess_token() == null || entity.getAccess_token().isEmpty()) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "access_token"));
        }

    }
    /**
     * 
     * Method that validates values of SessionVO class properties.
     * 
     * @param vo Instance of the session class, with value referring to the user session.
     * 
     **/
    private void validateVO(SessionVO vo) {

    }
}
