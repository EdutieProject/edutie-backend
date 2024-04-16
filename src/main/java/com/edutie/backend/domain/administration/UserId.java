package com.edutie.backend.domain.administration;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record UserId(@JsonValue UUID identifierValue) implements Serializable {
    public UserId(){
        this(UUID.randomUUID());
    }
}
