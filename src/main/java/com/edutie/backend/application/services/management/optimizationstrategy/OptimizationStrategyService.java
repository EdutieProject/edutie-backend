package com.edutie.backend.application.services.management.optimizationstrategy;

import com.edutie.backend.application.services.common.servicebase.GenericCrudService;
import com.edutie.backend.domain.personalization.optimizationstrategies.base.OptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.identities.OptimizationStrategyId;


//TODO: resolve what to do about optimizationStrategies
public interface OptimizationStrategyService<T extends Enum<T>> extends GenericCrudService<OptimizationStrategy<T>, OptimizationStrategyId> {
}
