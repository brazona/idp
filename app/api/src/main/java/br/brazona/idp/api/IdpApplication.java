package br.brazona.idp.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static br.brazona.idp.api.domain.constants.Messages.ACTIVE_PROFILE;
import static br.brazona.idp.api.domain.constants.Messages.STARTED_APPLICATION;

@Slf4j
@EnableAutoConfiguration
@EntityScan(basePackages = { "br.brazona.idp.api.infrastructure.entities" })
@ComponentScan( basePackages = {"br.brazona.idp.api.*"})
@EnableJpaRepositories(basePackages = {"br.brazona.idp.api.infrastructure.repositories"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableCaching
@SpringBootApplication
public class IdpApplication {

    @Value("${spring.profiles.active}")
    public static String profile;

    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);
        log.info(STARTED_APPLICATION);
        log.info(ACTIVE_PROFILE, profile);

    }
}
