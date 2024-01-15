package com.edutie.edutiebackend.domain.core.learningresult.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record SkillAssessmentId(@JsonValue UUID identifierValue) implements Serializable {
    public SkillAssessmentId(){
        this(UUID.randomUUID());
    }
}
