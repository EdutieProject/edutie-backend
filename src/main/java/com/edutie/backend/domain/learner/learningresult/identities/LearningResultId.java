package com.edutie.backend.domain.learner.learningresult.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record LearningResultId(@JsonValue UUID identifierValue) implements Serializable {
    public LearningResultId(){
        this(UUID.randomUUID());
    }
}
