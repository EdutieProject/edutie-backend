package com.edutie.edutiebackend.domain.core.lesson.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LessonId(@JsonValue UUID value) implements Serializable {
    public LessonId(){
        this(UUID.randomUUID());
    }
}
