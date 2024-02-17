package com.edutie.backend.infrastucture.persistence.contexts.personalization;

import com.edutie.backend.domain.personalization.optimizationstrategies.base.OptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.identities.OptimizationStrategyId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface OptimizationStrategyPersistenceContext extends PersistenceContext<OptimizationStrategy<?>, OptimizationStrategyId> {
}
