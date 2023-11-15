package com.edutie.edutiebackend.domain.skill.identities;

import java.io.Serializable;
import java.util.UUID;

public record SkillId(UUID Id) implements Serializable {
    public SkillId(){
        this(UUID.randomUUID());
    }
}
