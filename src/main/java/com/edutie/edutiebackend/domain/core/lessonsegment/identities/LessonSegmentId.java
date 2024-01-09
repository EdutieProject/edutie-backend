package com.edutie.edutiebackend.domain.core.lessonsegment.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record LessonSegmentId(@JsonValue UUID value) implements Serializable {
    public LessonSegmentId(){
        this(UUID.randomUUID());
    }
}
