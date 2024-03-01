package com.edutie.backend.domain.education.psychologist.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record PsychologistId(@JsonValue UUID identifierValue) implements Serializable {
    public PsychologistId(){
        this(UUID.randomUUID());
    }
}
