package com.oxygen.oxygenspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OxygenSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(OxygenSpringApplication.class, args);
    }

}
