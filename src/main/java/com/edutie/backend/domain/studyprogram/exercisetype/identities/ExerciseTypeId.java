package com.edutie.backend.domain.studyprogram.exercisetype.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record ExerciseTypeId(@JsonValue UUID identifierValue) implements Serializable {
    public ExerciseTypeId(){
        this(UUID.randomUUID());
    }
}
