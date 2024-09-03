package com.radiology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.selecteur.controller", "com.example.selecteur.model", "com.example.selecteur.repository"})
@EntityScan(basePackages = {"com.example.selecteur.model"})
@EnableJpaRepositories(basePackages = {"com.example.selecteur.repository"})
public class SelecteurSolutionsMedicalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelecteurSolutionsMedicalesApplication.class, args);
    }
}
