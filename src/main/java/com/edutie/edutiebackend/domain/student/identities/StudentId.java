package com.edutie.edutiebackend.domain.student.identities;

import java.io.Serializable;
import java.util.UUID;

public record StudentId(UUID Id) implements Serializable {
    public StudentId(){
        this(UUID.randomUUID());
    }
}
