package com.edutie.backend.domain.common.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record AdminId(@JsonValue UUID identifierValue) implements Serializable {
    public AdminId() {
        this(UUID.randomUUID());
    }
}
