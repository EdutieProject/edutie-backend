package com.edutie.edutiebackend.domain.core.learningresult.identities;

import java.io.Serializable;
import java.util.UUID;

public record LearningResultId(UUID value) implements Serializable {
    public LearningResultId(){
        this(UUID.randomUUID());
    }
}
