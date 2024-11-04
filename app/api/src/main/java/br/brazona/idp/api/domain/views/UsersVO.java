package br.brazona.idp.api.domain.views;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UsersVO implements GenericVO {

    private Long id;
    private String username;
    private String password;
}
