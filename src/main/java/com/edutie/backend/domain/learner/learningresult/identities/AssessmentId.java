package com.edutie.backend.domain.learner.learningresult.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record AssessmentId(@JsonValue UUID identifierValue) implements Serializable {
    public AssessmentId(){
        this(UUID.randomUUID());
    }
}
