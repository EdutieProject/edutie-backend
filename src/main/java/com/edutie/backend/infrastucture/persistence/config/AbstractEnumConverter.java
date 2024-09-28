package com.edutie.backend.infrastucture.persistence.config;

import com.edutie.backend.domain.common.enums.PersistableEnum;
import jakarta.persistence.*;

@Converter
public abstract class AbstractEnumConverter<T extends Enum<T> & PersistableEnum<E>, E> implements AttributeConverter<T, E> {
	private final Class<T> clazz;

	public AbstractEnumConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public E convertToDatabaseColumn(T attribute) {
		return attribute != null ? attribute.getCode() : null;
	}

	@Override
	public T convertToEntityAttribute(E dbData) {
		T[] enums = clazz.getEnumConstants();

		for (T e: enums) {
			if (e.getCode().equals(dbData)) {
				return e;
			}
		}

		throw new UnsupportedOperationException();
	}
}
