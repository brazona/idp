package br.brazona.idp.api.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users_permissions")
@Getter
@Setter
@Component
public class UserPermissionsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="users_permissions_sequence")
    @SequenceGenerator(name="users_permissions_sequence", sequenceName="users_permissions_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "bp_id")
    private BusinessPartnersEntity bp;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity role;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServicesProviderEntity service;
}
