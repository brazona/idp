package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SessionVO {
    private Long id;
    private Long user_id;
    private String jwt_token;
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;
    private String username;

    public SessionVO() {
    }

    public SessionVO(Long id, Long user_id, String jwt_token, String access_token,
                     String expires_in, String refresh_expires_in, String refresh_token) {
        this.id = id;
        this.user_id = user_id;
        this.jwt_token = jwt_token;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_expires_in = refresh_expires_in;
        this.refresh_token = refresh_token;
    }

    public SessionVO(Long user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }

    public SessionVO(String username, String access_token) {
        this.username = username;
        this.access_token = access_token;
    }

    public SessionVO(Long id, Long user_id, String access_token) {
        this.id = id;
        this.user_id = user_id;
        this.access_token = access_token;
    }
}
