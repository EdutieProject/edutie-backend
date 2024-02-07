package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityOptimizationStrategyRepository extends JpaRepository<AbilityOptimizationStrategy, OptimizationStrategyId> {
}
