package com.edutie.backend.domain.core.learningrequirement.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record LearningRequirementId(@JsonValue UUID identifierValue) implements Serializable {
    public LearningRequirementId(){
        this(UUID.randomUUID());
    }
}
