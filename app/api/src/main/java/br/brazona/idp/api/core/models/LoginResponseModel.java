package br.brazona.idp.api.core.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Setter
@Component
@Getter
public class LoginResponseModel {
    private String result;

    public LoginResponseModel() {}
    public LoginResponseModel(String result) {
        this.result = result;
    }


}
