package com.edutie.edutiebackend.domain.core.skill.identities;

import java.io.Serializable;
import java.util.UUID;

public record SkillId(UUID value) implements Serializable {
    public SkillId(){
        this(UUID.randomUUID());
    }
}
