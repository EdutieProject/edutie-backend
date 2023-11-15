package com.edutie.edutiebackend.domain.course.identities;

import java.io.Serializable;
import java.util.UUID;

public record CourseId(UUID Id) implements Serializable {
    public CourseId(){
        this(UUID.randomUUID());
    }
}
