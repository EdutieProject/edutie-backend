package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.OptimizationStrategy;

import java.util.UUID;


//TODO: resolve what to do about optimizationStrategies
public interface OptimizationStrategyService<T extends Enum<T>> extends GenericCrudService<OptimizationStrategy<T>, UUID> {
}
