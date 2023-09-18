package com.oxygen.oxygenspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OxygenSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(OxygenSpringApplication.class, args);
    }

}
