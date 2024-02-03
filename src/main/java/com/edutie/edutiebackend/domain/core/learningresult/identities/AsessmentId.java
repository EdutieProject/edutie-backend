package com.edutie.edutiebackend.domain.core.learningresult.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record AsessmentId(@JsonValue UUID identifierValue) implements Serializable {
    public AsessmentId(){
        this(UUID.randomUUID());
    }
}
