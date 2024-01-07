package com.edutie.edutiebackend.domain.core.student.identities;

import java.io.Serializable;
import java.util.UUID;

public record LearningParameterId(UUID value) implements Serializable {
    public LearningParameterId(){
        this(UUID.randomUUID());
    }
}
