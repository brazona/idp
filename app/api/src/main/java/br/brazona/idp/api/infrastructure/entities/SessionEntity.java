package br.brazona.idp.api.infrastructure.entities;


import br.brazona.idp.api.domain.dto.SessionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "session")
@Getter
@Component
public class SessionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Long user_id;
    private String jwt_token;
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;

    public SessionEntity() {
    }

    public SessionEntity(SessionDTO sessionDTO) {
        this.id = sessionDTO.getId();
        this.user_id = sessionDTO.getUser_id();
        this.jwt_token = sessionDTO.getJwt_token();
        this.access_token = sessionDTO.getAccess_token();
        this.expires_in = sessionDTO.getExpires_in();
        this.refresh_expires_in = sessionDTO.getRefresh_expires_in();
        this.refresh_token = sessionDTO.getRefresh_token();
    }
}
