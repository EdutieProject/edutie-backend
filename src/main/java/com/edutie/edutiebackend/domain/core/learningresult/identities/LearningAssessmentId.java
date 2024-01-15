package com.edutie.edutiebackend.domain.core.learningresult.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.UUID;

public record LearningAssessmentId(@JsonValue UUID identifierValue) implements Serializable {
    public LearningAssessmentId(){
        this(UUID.randomUUID());
    }
}
