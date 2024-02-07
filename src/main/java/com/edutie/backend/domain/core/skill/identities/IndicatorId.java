package com.edutie.backend.domain.core.skill.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record IndicatorId(@JsonValue UUID identifierValue) implements Serializable {
    public IndicatorId(){
        this(UUID.randomUUID());
    }
}
