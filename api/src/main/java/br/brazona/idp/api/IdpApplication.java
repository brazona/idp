package br.brazona.idp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class IdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdpApplication.class, args);

    }

}
