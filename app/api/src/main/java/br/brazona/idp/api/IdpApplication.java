package br.brazona.idp.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Slf4j
@EnableAutoConfiguration
@EntityScan(basePackages = { "br.brazona.idp.api.infrastructure.entities" })
@ComponentScan( basePackages = {"br.brazona.idp.api.*"})
@EnableJpaRepositories(basePackages = {"br.brazona.idp.api.infrastructure.repositories"})
@EnableTransactionManagement
@RestController
@EnableCaching
@SpringBootApplication
public class IdpApplication {
    /**
     *
     * Class constructor.
     *
     **/
    public IdpApplication() {
    }

    /**
     *
     * Class Main.
     *
     **/
    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);
        log.error("br.brazona.idp.api: LOG ERROR");
        log.warn("br.brazona.idp.api: LOG WARN");
        log.info("br.brazona.idp.api: LOG INFO");
        log.debug("br.brazona.idp.api: LOG DEBUG");
        log.trace("br.brazona.idp.api: LOG TRACE");

    }
}
