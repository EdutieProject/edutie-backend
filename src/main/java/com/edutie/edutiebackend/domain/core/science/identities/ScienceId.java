package com.edutie.edutiebackend.domain.core.science.identities;

import java.io.Serializable;
import java.util.UUID;

public record ScienceId(UUID value)implements Serializable {
    public ScienceId(){
        this(UUID.randomUUID());
    }
}
