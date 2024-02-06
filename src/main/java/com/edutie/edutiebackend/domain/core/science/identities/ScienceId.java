package com.edutie.edutiebackend.domain.core.science.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record ScienceId(@JsonValue UUID identifierValue) implements Serializable {
    public ScienceId(){
        this(UUID.randomUUID());
    }
}
