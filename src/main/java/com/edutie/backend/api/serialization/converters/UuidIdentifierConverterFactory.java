package com.edutie.backend.api.serialization.converters;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidIdentifierConverterFactory implements ConverterFactory<String, UuidIdentifier> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static class StringToUuidIdentifierConverter<T extends UuidIdentifier> implements Converter<String, T> {
        private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
        private Class<T> idType;

        public StringToUuidIdentifierConverter(Class<T> idType) {
            this.idType = idType;
        }

        public T convert(String source) {
            try {
                return idType.getConstructor(UUID.class).newInstance(UUID.fromString(source));
            } catch (Exception e) {
                LOGGER.warn("Could not parse provided string into UUID identifier");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public <T extends UuidIdentifier> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToUuidIdentifierConverter<>(targetType);
    }
}
