package com.edutie.edutiebackend.domain.core.lessonsegment.identities;

import java.io.Serializable;
import java.util.UUID;

public record ExerciseTypeId(UUID Id) implements Serializable {
    public ExerciseTypeId(){
        this(UUID.randomUUID());
    }
}
