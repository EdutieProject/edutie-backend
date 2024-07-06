package com.edutie.backend.api.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OpenApiConfig {

    /**
     * Following function adds JWT auth to swagger
     * @return OpenAPI config
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                //defining security scheme
                .components(new Components()
                        .addSecuritySchemes("JWTAuthentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                //setting global security
                .security(List.of(new SecurityRequirement().addList("JWTAuthentication")));
    }
}
