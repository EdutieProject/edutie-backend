package com.edutie.backend.domain.common.base.identity;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.MappedSuperclass;

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
}
