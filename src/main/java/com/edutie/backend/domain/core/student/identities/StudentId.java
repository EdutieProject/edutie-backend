package com.edutie.backend.domain.core.student.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record StudentId(@JsonValue UUID identifierValue) implements Serializable {
    public StudentId(){
        this(UUID.randomUUID());
    }
}
