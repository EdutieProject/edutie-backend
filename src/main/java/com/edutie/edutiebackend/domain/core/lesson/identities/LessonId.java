package com.edutie.edutiebackend.domain.core.lesson.identities;

import java.io.Serializable;
import java.util.UUID;

public record LessonId(UUID value) implements Serializable {
    public LessonId(){
        this(UUID.randomUUID());
    }
}
