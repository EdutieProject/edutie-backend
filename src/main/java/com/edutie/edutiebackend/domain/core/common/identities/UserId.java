package com.edutie.edutiebackend.domain.core.common.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record UserId(@JsonValue UUID identifierValue) implements Serializable {
    public UserId(){
        this(UUID.randomUUID());
    }
}
