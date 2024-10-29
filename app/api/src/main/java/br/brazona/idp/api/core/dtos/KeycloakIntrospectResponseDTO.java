package br.brazona.idp.api.core.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class KeycloakIntrospectResponseDTO {

    private boolean active;
    public KeycloakIntrospectResponseDTO(){}
    public KeycloakIntrospectResponseDTO(boolean active){
        this.active = active;
    }
}
