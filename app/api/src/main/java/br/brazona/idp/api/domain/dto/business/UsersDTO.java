package br.brazona.idp.api.domain.dto.business;

import br.brazona.idp.api.domain.views.UsersVO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UsersDTO{

    public UsersEntity toEntity(UsersVO vo) {
        return new UsersEntity(

        );
    }

    public UsersVO toVO(UsersEntity entity) {
        return new UsersVO(

        );
    }
}
