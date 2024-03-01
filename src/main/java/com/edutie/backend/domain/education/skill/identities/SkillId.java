package com.edutie.backend.domain.education.skill.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record SkillId(@JsonValue UUID identifierValue) implements Serializable {
    public SkillId(){
        this(UUID.randomUUID());
    }
}
