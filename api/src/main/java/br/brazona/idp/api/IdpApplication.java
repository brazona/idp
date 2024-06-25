package br.brazona.idp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = { "br.brazona.idp.api.persistence.entities" })
@ComponentScan( basePackages = {"br.brazona.idp.api.*", "org.springframework.http.*"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"br.brazona.idp.api.persistence.repositories"})
@EnableAutoConfiguration
public class IdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);

    }

}
