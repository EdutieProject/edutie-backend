package com.edutie.edutiebackend.domain.optimizationstrategies;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.optimizationstrategies.identities.OptimizationStrategyId;
import com.edutie.edutiebackend.domain.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class IntelligenceOptimizationStrategy extends EntityBase<OptimizationStrategyId> {
    GenerationPrompt prompt;
    Intelligence intelligence;
    Double requiredValue;
}
