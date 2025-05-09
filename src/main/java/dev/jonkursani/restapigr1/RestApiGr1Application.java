package dev.jonkursani.restapigr1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableMethodSecurity // per annotation - based authorization
public class RestApiGr1Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr1Application.class, args);
    }

}
