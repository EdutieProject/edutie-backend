package com.edutie.edutiebackend.domain.core.course.identities;

import java.io.Serializable;
import java.util.UUID;

public record CourseId(UUID value) implements Serializable {
    public CourseId(){
        this(UUID.randomUUID());
    }
}
