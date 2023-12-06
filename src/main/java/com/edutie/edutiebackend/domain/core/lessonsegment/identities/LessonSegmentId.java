package com.edutie.edutiebackend.domain.core.lessonsegment.identities;

import java.io.Serializable;
import java.util.UUID;

public record LessonSegmentId(UUID Id) implements Serializable {
    public LessonSegmentId(){
        this(UUID.randomUUID());
    }
}
