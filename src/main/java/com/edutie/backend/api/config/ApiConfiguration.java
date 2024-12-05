package com.edutie.backend.api.config;

import com.edutie.backend.api.serialization.converters.UuidIdentifierConverterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.format.*;
import org.springframework.web.servlet.config.annotation.*;
import lombok.*;

@Configuration
public class ApiConfiguration implements WebMvcConfigurer {
	@Value("${host-main-url}")
	private String HOST_MAIN_URL;

	@Override
	public void addFormatters(@NonNull FormatterRegistry registry) {
		registry.addConverterFactory(new UuidIdentifierConverterFactory());
	}

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(
				HOST_MAIN_URL,
				"http://127.0.0.1:5173",
				"http://localhost:5173"
		);
	}
}
