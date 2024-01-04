package com.edutie.edutiebackend.domain.core.student.identities;

import java.io.Serializable;
import java.util.UUID;

public record StudentId(UUID value) implements Serializable {
    public StudentId(){
        this(UUID.randomUUID());
    }
}
