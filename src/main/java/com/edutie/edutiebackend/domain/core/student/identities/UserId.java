package com.edutie.edutiebackend.domain.core.student.identities;

import java.io.Serializable;
import java.util.UUID;

public record UserId(UUID Id) implements Serializable {
    public UserId(){
        this(UUID.randomUUID());
    }
}
