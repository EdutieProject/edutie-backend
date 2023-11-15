package com.edutie.edutiebackend.domain.student.identities;

import java.io.Serializable;
import java.util.UUID;

public record LearningParametersId(UUID Id) implements Serializable {
    public LearningParametersId(){
        this(UUID.randomUUID());
    }
}
