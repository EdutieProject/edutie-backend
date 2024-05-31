package com.edutie.backend.api.config;

import com.edutie.backend.api.serialization.converters.UuidIdentifierConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new UuidIdentifierConverterFactory());
    }
}
