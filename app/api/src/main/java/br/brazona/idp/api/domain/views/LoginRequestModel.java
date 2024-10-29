package br.brazona.idp.api.domain.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestModel {

    private String username;
    private String password;
}
