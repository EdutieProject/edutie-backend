package com.edutie.edutiebackend.domain.optimizationstrategies.identities;

import java.io.Serializable;
import java.util.UUID;

public record OptimizationStrategyId(UUID Id) implements Serializable {
    public OptimizationStrategyId(){
        this(UUID.randomUUID());
    }
}
