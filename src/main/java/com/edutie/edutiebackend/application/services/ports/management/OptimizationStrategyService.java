package com.edutie.edutiebackend.application.services.ports.management;

import com.edutie.edutiebackend.application.services.ports.common.GenericCrudService;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.OptimizationStrategy;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;


//TODO: resolve what to do about optimizationStrategies
public interface OptimizationStrategyService<T extends Enum<T>>
        extends GenericCrudService<OptimizationStrategy<T>, OptimizationStrategyId> {
}
