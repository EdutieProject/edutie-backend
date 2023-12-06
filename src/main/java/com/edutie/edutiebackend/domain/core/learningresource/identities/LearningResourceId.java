package com.edutie.edutiebackend.domain.core.learningresource.identities;

import java.io.Serializable;
import java.util.UUID;

public record LearningResourceId(UUID Id) implements Serializable {
    public LearningResourceId(){
        this(UUID.randomUUID());
    }
}
