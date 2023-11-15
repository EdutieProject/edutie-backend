package com.edutie.edutiebackend.domain.lesson.identities;

import java.io.Serializable;
import java.util.UUID;

public record LessonId(UUID Id) implements Serializable {
    public LessonId(){
        this(UUID.randomUUID());
    }
}
