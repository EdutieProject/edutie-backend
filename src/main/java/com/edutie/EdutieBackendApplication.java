package com.edutie;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Edutie System", summary = "Edutie Backend Application", version = "1-beta.0.0"))
public class EdutieBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdutieBackendApplication.class, args);
	}

}
