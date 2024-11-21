package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.views.business.SessionVO;
import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionDTO {

    public SessionVO toVO(SessionEntity entity) {
        return new SessionVO(entity.getId(), entity.getUser_id(), entity.getAccess_token());
    }

    public SessionEntity toEntity(SessionVO vo) {
        return new SessionEntity(vo.getUser_id(), vo.getAccess_token());
    }

    public SessionEntity toEntityByID(SessionVO vo) {
        return new SessionEntity(vo.getId(), vo.getUser_id(), vo.getAccess_token());
    }
}
