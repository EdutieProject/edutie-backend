package com.edutie.backend.api.config;

import com.edutie.backend.api.serialization.converters.UuidIdentifierConverterFactory;
import org.springframework.context.annotation.*;
import org.springframework.format.*;
import org.springframework.web.servlet.config.annotation.*;
import lombok.*;

@Configuration
public class ApiConfig implements WebMvcConfigurer {
	@Override
	public void addFormatters(@NonNull FormatterRegistry registry) {
		registry.addConverterFactory(new UuidIdentifierConverterFactory());
	}

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		//TODO: add allowed origins to env or props
		registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5173", "http://localhost:5173");
	}
}
