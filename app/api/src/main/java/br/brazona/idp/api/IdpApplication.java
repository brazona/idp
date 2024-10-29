package br.brazona.idp.api;

import br.brazona.idp.api.domain.services.keycloak.IAuthService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static br.brazona.idp.api.domain.constants.Messages.ACTIVE_PROFILE;
import static br.brazona.idp.api.domain.constants.Messages.STARTED_APPLICATION;

@Slf4j
@EnableAutoConfiguration
@EnableFeignClients(clients = IAuthService.class)
@SpringBootApplication
@EntityScan(basePackages = { "br.brazona.idp.api.persistence.entities" })
@ComponentScan( basePackages = {"br.brazona.idp.api.*"})
@EnableJpaRepositories(basePackages = {"br.brazona.idp.api.persistence.repositories"})
public class IdpApplication {
    @Value("${spring.profiles.active}")
    public static String profile;
    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);
        log.info(STARTED_APPLICATION);
        log.info(ACTIVE_PROFILE, profile);

    }
}
