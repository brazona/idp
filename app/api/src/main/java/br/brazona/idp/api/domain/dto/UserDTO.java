package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.views.business.UserRequestVO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDTO {

    public UsersEntity toEntity(UserRequestVO vo) {
        return new UsersEntity(

        );
    }

}
