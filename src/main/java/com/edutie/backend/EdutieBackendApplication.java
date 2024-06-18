package com.edutie.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Edutie API", summary = "Edutie backend app", version = "0.1"))
public class EdutieBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdutieBackendApplication.class, args);
    }

}
