package com.edutie.backend.domain.common.base.identity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base Identifier class
 *
 * @param <TId> The identifier type
 */
@MappedSuperclass
public abstract class Identifier<TId> implements Serializable {
	protected final TId identifierValue;

	public Identifier(TId value) {
		identifierValue = value;
	}

	@JsonValue
	public TId identifierValue() {
		return identifierValue;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		Identifier<?> that = (Identifier<?>) object;
		return Objects.equals(identifierValue, that.identifierValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifierValue);
	}

	@Override
	public String toString() {
		return identifierValue.toString();
	}
}
