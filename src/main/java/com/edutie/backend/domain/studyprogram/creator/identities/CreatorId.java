package com.edutie.backend.domain.studyprogram.creator.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record CreatorId(@JsonValue UUID identifierValue) implements Serializable {
    public CreatorId(){
        this(UUID.randomUUID());
    }
}
