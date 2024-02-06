package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligenceOptimizationStrategyRepository extends JpaRepository<IntelligenceOptimizationStrategy, OptimizationStrategyId> {
}
