package com.edutie.edutiebackend.domain.core.optimizationstrategies.identities;

import java.io.Serializable;
import java.util.UUID;

public record OptimizationStrategyId(UUID value) implements Serializable {
    public OptimizationStrategyId(){
        this(UUID.randomUUID());
    }
}
