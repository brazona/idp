package br.brazona.idp.api.core.dtos.business;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Setter
@Component
@Getter
public class LoginResponseDTO {
    private String result;

    public LoginResponseDTO() {}
    public LoginResponseDTO(String result) {
        this.result = result;
    }


}