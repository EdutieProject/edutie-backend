package com.edutie.backend.domain.core.optimizationstrategies.identities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.UUID;

public record OptimizationStrategyId(@JsonValue UUID identifierValue) implements Serializable {
    public OptimizationStrategyId(){
        this(UUID.randomUUID());
    }
}
