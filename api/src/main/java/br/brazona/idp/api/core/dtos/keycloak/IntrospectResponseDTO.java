package br.brazona.idp.api.core.dtos.keycloak;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class IntrospectResponseDTO {

    private boolean active;
    public IntrospectResponseDTO(){}
    public IntrospectResponseDTO(boolean active){
        this.active = active;
    }
}