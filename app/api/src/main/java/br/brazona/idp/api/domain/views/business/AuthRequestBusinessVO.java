package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AuthRequestBusinessVO extends AbstractVO {


    private Long id;
    private String username;
    private String password;

    public AuthRequestBusinessVO() {
    }

    public AuthRequestBusinessVO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    public AuthRequestBusinessVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
