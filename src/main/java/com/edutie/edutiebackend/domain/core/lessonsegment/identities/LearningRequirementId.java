package com.edutie.edutiebackend.domain.core.lessonsegment.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record LearningRequirementId(@JsonValue UUID value) implements Serializable {
    public LearningRequirementId(){
        this(UUID.randomUUID());
    }
}
