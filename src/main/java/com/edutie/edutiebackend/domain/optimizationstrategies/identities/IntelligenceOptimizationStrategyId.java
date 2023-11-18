package com.edutie.edutiebackend.domain.optimizationstrategies.identities;

import java.io.Serializable;
import java.util.UUID;

public record IntelligenceOptimizationStrategyId(UUID Id) implements Serializable {
    public IntelligenceOptimizationStrategyId(){
        this(UUID.randomUUID());
    }
}
