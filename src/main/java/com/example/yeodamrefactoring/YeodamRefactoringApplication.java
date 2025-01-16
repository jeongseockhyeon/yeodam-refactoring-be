package com.example.yeodamrefactoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YeodamRefactoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeodamRefactoringApplication.class, args);
    }

}
