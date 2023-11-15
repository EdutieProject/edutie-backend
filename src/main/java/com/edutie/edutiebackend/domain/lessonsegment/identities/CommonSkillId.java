package com.edutie.edutiebackend.domain.lessonsegment.identities;

import java.io.Serializable;
import java.util.UUID;

public record CommonSkillId(UUID Id) implements Serializable {
    public CommonSkillId(){
        this(UUID.randomUUID());
    }
}
