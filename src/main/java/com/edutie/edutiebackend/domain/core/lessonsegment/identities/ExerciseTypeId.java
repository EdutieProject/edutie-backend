package com.edutie.edutiebackend.domain.core.lessonsegment.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record ExerciseTypeId(@JsonValue UUID value) implements Serializable {
    public ExerciseTypeId(){
        this(UUID.randomUUID());
    }
}
