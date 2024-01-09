package com.edutie.edutiebackend.domain.core.skill.identities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.PersistenceContexts;

import java.io.Serializable;
import java.util.UUID;

public record SkillId(@JsonValue UUID value) implements Serializable {
    public SkillId(){
        this(UUID.randomUUID());
    }
}
