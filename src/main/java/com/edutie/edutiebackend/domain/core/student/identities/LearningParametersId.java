package com.edutie.edutiebackend.domain.core.student.identities;

import java.io.Serializable;
import java.util.UUID;

public record LearningParametersId(UUID value) implements Serializable {
    public LearningParametersId(){
        this(UUID.randomUUID());
    }
}
