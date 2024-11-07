package br.brazona.idp.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
@EnableFeignClients
public class IdpApplication {


    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);
        log.error("br.brazona.idp.api: LOG ERROR");
        log.warn("br.brazona.idp.api: LOG WARN");
        log.info("br.brazona.idp.api: LOG INFO");
        log.debug("br.brazona.idp.api: LOG DEBUG");
        log.trace("br.brazona.idp.api: LOG TRACE");

    }
}
