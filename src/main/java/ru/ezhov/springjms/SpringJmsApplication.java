package ru.ezhov.springjms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringJmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
