package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.personalization.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.identities.OptimizationStrategyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligenceOptimizationStrategyRepository extends JpaRepository<IntelligenceOptimizationStrategy, OptimizationStrategyId> {
}
