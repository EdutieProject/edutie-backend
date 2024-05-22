package com.edutie.backend.domain.education.learningrequirement.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record SubRequirementId(@JsonValue UUID identifierValue) implements Serializable {
    public SubRequirementId(){
        this(UUID.randomUUID());
    }
}
