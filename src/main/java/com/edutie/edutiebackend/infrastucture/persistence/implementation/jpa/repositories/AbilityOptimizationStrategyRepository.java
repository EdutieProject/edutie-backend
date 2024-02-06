package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityOptimizationStrategyRepository extends JpaRepository<AbilityOptimizationStrategy, OptimizationStrategyId> {
}
