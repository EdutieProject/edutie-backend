package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligenceOptimizationStrategyRepository extends JpaRepository<IntelligenceOptimizationStrategy, OptimizationStrategyId> {
}
