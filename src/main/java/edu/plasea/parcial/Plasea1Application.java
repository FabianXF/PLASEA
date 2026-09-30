package edu.plasea.parcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.plasea.parcial", "edu.plasea.parcial.dto"})
@EntityScan(basePackages = {"edu.plasea.parcial.model"})
@EnableJpaRepositories(basePackages = {"edu.plasea.parcial.repository"})
public class Plasea1Application {

    public static void main(String[] args) {
        SpringApplication.run(Plasea1Application.class, args);
    }
}