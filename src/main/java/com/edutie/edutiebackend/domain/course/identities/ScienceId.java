package com.edutie.edutiebackend.domain.course.identities;

import java.io.Serializable;
import java.util.UUID;

public record ScienceId(UUID Id)implements Serializable {
    public ScienceId(){
        this(UUID.randomUUID());
    }
}
