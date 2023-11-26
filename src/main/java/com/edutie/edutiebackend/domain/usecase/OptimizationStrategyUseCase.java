package com.edutie.edutiebackend.domain.usecase;

import com.edutie.edutiebackend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.edutiebackend.domain.core.student.Student;

import java.util.Set;

public interface OptimizationStrategyUseCase {
    /**
     * Selects ability optimization traits that best match given student
     *
     * @param student student
     * @return Set of Ability Optimization Strategies
     */
    Set<AbilityOptimizationStrategy> selectAbilityOptimizationStrategies(Student student);

    /**
     * Selects intelligence optimization traits that best match given student
     *
     * @param student student
     * @return Set of Intelligence Optimization Strategies
     */
    Set<IntelligenceOptimizationStrategy> selectIntelligenceOptimizationStrategies(Student student);
}
