package com.edutie.edutiebackend.domain.core.course.identities;

import java.io.Serializable;
import java.util.UUID;

public record ScienceId(UUID Id)implements Serializable {
    public ScienceId(){
        this(UUID.randomUUID());
    }
}
