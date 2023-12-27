package com.edutie.edutiebackend.domain.core.lessonsegment.identities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record LearningRequirementId(UUID Id) implements Serializable {
    public LearningRequirementId(){
        this(UUID.randomUUID());
    }
}
