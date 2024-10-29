package br.brazona.idp.api.persistence.entities;

import br.brazona.idp.api.core.dtos.business.SessionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "session")
@Getter
@Setter
@Component
public class SessionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="session_sequence")
    @SequenceGenerator(name="session_sequence", sequenceName="session_seq", allocationSize = 1)
    private Long id;
    private Long user_id;
    @Column(nullable=false, length=10012)
    private String jwt_token;
    @Column(nullable=false, length=10012)
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    @Column(nullable=false, length=10012)
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
