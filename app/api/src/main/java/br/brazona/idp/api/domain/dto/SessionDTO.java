/**
 * @author Brazona Tech
 **/
package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.BadRequestException;
import br.brazona.idp.api.domain.exceptions.NotFoundException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.views.business.SessionVO;
import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionDTO {
    @Autowired
    private ExceptionUtil exceptionUtil;

    public SessionVO toVO(SessionEntity entity) {
        validateEntity(entity);
        return new SessionVO(entity.getId(), entity.getUser_id(), entity.getAccess_token());
    }
    /**
     * @param vo Instance of the session class, with value referring to the user session.
     * @return SessionEntity Entity class x table relationship.
     **/
    public SessionEntity toEntity(SessionVO vo) {
        return new SessionEntity(vo.getUser_id(), vo.getAccess_token());
    }
    /**
     * @param vo Instance of the session class, with value referring to the user session.
     * @return SessionEntity Entity class x table relationship.
     **/
    public SessionEntity toEntityByID(SessionVO vo) {
        return new SessionEntity(vo.getId(), vo.getUser_id(), vo.getAccess_token());
    }
    /**
     * @param entity Instance of the session class, with value referring to the user session.
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

    private void validateVO(SessionVO vo) {

    }
}
