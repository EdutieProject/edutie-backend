package com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record ExternalSourceId(@Id UUID id) implements Serializable {
    public ExternalSourceId()
    {
        this(UUID.randomUUID());
    }
}
