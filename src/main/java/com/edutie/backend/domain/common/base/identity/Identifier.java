package com.edutie.backend.domain.common.base.identity;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public abstract class Identifier<TId> {
    @JsonValue
    protected TId identifierValue;

    public Identifier(TId value) {
        identifierValue = value;
    }

    public TId identifierValue() {
        return identifierValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Identifier<?> that = (Identifier<?>) object;
        return Objects.equals(identifierValue, that.identifierValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifierValue);
    }
}
