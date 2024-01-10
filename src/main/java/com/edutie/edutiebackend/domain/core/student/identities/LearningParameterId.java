package com.edutie.edutiebackend.domain.core.student.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LearningParameterId(@JsonValue UUID value) implements Serializable {
    public LearningParameterId(){
        this(UUID.randomUUID());
    }
}
