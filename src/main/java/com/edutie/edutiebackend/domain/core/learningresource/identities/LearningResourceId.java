package com.edutie.edutiebackend.domain.core.learningresource.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LearningResourceId(@JsonValue UUID identifierValue) implements Serializable {
    public LearningResourceId(){
        this(UUID.randomUUID());
    }
}
