package com.edutie.edutiebackend.application.services.ports.crud;

import com.edutie.edutiebackend.application.services.ports.crud.base.BaseService;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.OptimizationStrategy;

import java.util.UUID;


//TODO: resolve what to do about optimizationStrategies
public interface OptimizationStrategyService extends BaseService<OptimizationStrategy, UUID> {
}
