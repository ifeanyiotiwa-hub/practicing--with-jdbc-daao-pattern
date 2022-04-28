package io.codewithme.daojdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("local")
@SpringBootApplication
public class DaoJdbcApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DaoJdbcApplication.class, args);
    }
    
}
