package com.edutie.edutiebackend.application.utils.resourcepersonalization;

import com.edutie.edutiebackend.domain.core.optimizationstrategies.OptimizationStrategy;
import com.edutie.edutiebackend.domain.core.student.Student;

import java.util.Set;

public class ResourcePersonalizationUtils {
    /**
     * Selects optimization strategies based on concrete trait
     * @return Set of selected optimization strategies
     * @param <T> trait
     */
    public static <T extends Enum<T>> Set<OptimizationStrategy<T>> selectOptimizationStrategiesForTrait(T trait, final Student student)
    {
        //TODO!
    }
}
