package br.brazona.idp.api.infrastructure.entities;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private Long user_id;
    private String jwt_token;
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;

    public SessionEntity() {
    }

    public SessionEntity(Long user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }

    public SessionEntity(Long id, Long user_id, String access_token) {
        this.id = id;
        this.user_id = user_id;
        this.access_token = access_token;
    }
}
