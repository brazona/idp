package br.brazona.idp.api.core.dtos.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EntityDTO {
    private String service_id;
    private String bo_id;

    public EntityDTO() {
    }

    public EntityDTO(String service_id, String bo_id) {
        this.service_id = service_id;
        this.bo_id = bo_id;
    }
}
