package br.brazona.idp.api.domain.dto.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserDTO {


    private Long id;
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
