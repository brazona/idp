package br.brazona.idp.api.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

    private String username;
    private String password;

    public UserDTO(String name) {
    }
}
