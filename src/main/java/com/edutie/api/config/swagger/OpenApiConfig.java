package com.edutie.api.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;


@Component
public class OpenApiConfig {
	@Value("${authorization-url}")
	private String authorizationUrl;
	@Value("${token-url}")
	private String tokenUrl;

	/**
	 * Following function adds JWT auth to swagger
	 *
	 * @return OpenAPI config
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				//defining security scheme
				.components(new Components().addSecuritySchemes("OAuth2", new SecurityScheme().type(SecurityScheme.Type.OAUTH2).flows(new OAuthFlows().authorizationCode(new OAuthFlow().authorizationUrl(authorizationUrl).tokenUrl(tokenUrl)))))
				//setting global security
				.security(List.of(new SecurityRequirement().addList("OAuth2")));
	}
}
