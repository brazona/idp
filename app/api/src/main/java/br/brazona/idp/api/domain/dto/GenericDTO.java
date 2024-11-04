package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.views.GenericVO;
import br.brazona.idp.api.infrastructure.entities.GenericEntity;

public interface GenericDTO {
    public GenericEntity toEntity(GenericVO vo);
    public GenericVO toVO(GenericEntity entity);
}
