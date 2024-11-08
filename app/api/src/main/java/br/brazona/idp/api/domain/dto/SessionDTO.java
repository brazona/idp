package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SessionDTO {
    private Long id;
    private Long user_id;
    private String jwt_token;
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;

    public SessionDTO() {
    }

    public SessionDTO(Long user_id, String jwt_token, String access_token,
                      String expires_in, String refresh_expires_in, String refresh_token) {
        this.user_id = user_id;
        this.jwt_token = jwt_token;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_expires_in = refresh_expires_in;
        this.refresh_token = refresh_token;
    }

    public SessionDTO(SessionEntity sessionEntity) {
        this.id = sessionEntity.getId();
        this.user_id = sessionEntity.getUser_id();
        this.jwt_token = sessionEntity.getJwt_token();
        this.access_token = sessionEntity.getAccess_token();
        this.expires_in = sessionEntity.getExpires_in();
        this.refresh_expires_in = sessionEntity.getRefresh_expires_in();
        this.refresh_token = sessionEntity.getRefresh_token();
    }
}
