package com.edutie.backend.domain.education.educator.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record EducatorId(@JsonValue UUID identifierValue) implements Serializable {
    public EducatorId(){
        this(UUID.randomUUID());
    }
}
