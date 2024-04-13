package com.edutie.backend.domain.studyprogram.segment.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record SegmentId(@JsonValue UUID identifierValue) implements Serializable {
    public SegmentId(){
        this(UUID.randomUUID());
    }
}
