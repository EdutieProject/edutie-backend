package com.edutie.edutiebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class EdutieBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdutieBackendApplication.class, args);
    }

}
