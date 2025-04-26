package com.edutie.api.serialization.converters;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import org.springframework.core.convert.converter.*;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.UUID;

@Component
@Slf4j
public class UuidIdentifierConverterFactory implements ConverterFactory<String, UuidIdentifier> {
	@Override
	public <T extends UuidIdentifier> @NonNull Converter<String, T> getConverter(@NonNull Class<T> targetType) {
		return new StringToUuidIdentifierConverter<>(targetType);
	}

	@Slf4j
	private record StringToUuidIdentifierConverter<T extends UuidIdentifier>(
			Class<T> idType) implements Converter<String, T> {

		public T convert(@NonNull String source) {
			try {
				return idType.getConstructor(UUID.class).newInstance(UUID.fromString(source));
			} catch (Exception e) {
				log.warn("Could not parse provided string into UUID identifier");
				throw new RuntimeException(e);
			}
		}
	}
}
