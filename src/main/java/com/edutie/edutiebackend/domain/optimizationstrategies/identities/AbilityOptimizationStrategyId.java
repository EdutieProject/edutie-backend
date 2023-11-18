package com.edutie.edutiebackend.domain.optimizationstrategies.identities;

import java.io.Serializable;
import java.util.UUID;

public record AbilityOptimizationStrategyId(UUID Id) implements Serializable {
    public AbilityOptimizationStrategyId(){
        this(UUID.randomUUID());
    }
}
