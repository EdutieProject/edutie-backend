package com.edutie.edutiebackend.dummy;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record SampleEntityId(@JsonValue UUID identifierValue) implements Serializable {
    public SampleEntityId(){
        this(UUID.randomUUID());
    }
}
