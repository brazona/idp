package br.brazona.idp.api.core.models;

import lombok.Setter;
import org.springframework.stereotype.Component;
@Setter
@Component
public class LoginResponseModel {
    private String result;

    public LoginResponseModel() {}
    public LoginResponseModel(String result) {
        this.result = result;
    }


}
