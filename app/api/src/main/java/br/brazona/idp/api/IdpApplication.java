package br.brazona.idp.api;

import br.brazona.idp.api.services.keycloak.IAuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableFeignClients(clients = IAuthService.class)
@SpringBootApplication
@EntityScan(basePackages = { "br.brazona.idp.api.persistence.entities" })
@ComponentScan( basePackages = {"br.brazona.idp.api.*"})
@EnableJpaRepositories(basePackages = {"br.brazona.idp.api.persistence.repositories"})
public class IdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);

    }
}
