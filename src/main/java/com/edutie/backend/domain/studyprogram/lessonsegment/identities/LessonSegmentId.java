package com.edutie.backend.domain.studyprogram.lessonsegment.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LessonSegmentId(@JsonValue UUID identifierValue) implements Serializable {
    public LessonSegmentId(){
        this(UUID.randomUUID());
    }
}
