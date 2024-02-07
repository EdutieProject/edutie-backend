package com.edutie.backend.domain.core.lesson.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LessonId(@JsonValue UUID identifierValue) implements Serializable {
    public LessonId(){
        this(UUID.randomUUID());
    }
}
