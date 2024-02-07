package com.edutie.backend.domain.core.course.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record CourseId(@JsonValue UUID identifierValue) implements Serializable {
    public CourseId(){
        this(UUID.randomUUID());
    }
}
