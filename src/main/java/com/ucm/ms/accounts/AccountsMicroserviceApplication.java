package com.ucm.ms.accounts;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

// TODO: Configure Spring Security later. Disabled until relevant.
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = {"com.ucm"})
public class AccountsMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsMicroserviceApplication.class, args);
    }
}
