package com.edutie.edutiebackend.domain.core.learningresult.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LearningAssessmentId(@JsonValue UUID value) implements Serializable {
    public LearningAssessmentId(){
        this(UUID.randomUUID());
    }
}
