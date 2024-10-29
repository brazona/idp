package br.brazona.idp.api.domain.dto.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    private String username;
    private String password;
}
