package br.brazona.idp.core.models;

import lombok.Getter;
import org.springframework.stereotype.Component;
@Getter
@Component
public class LoginRequestModel {

    private String username;
    private String password;
}
