package com.edutie.edutiebackend.domain.core.optimizationstrategies;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.IntelligenceOptimizationStrategyId;
import com.edutie.edutiebackend.domain.core.common.generationprompt.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class responsible for optimizing learning resources based
 * on required intelligence parameter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class IntelligenceOptimizationStrategy extends AuditableEntityBase<IntelligenceOptimizationStrategyId> {
    GenerationPrompt prompt;
    Intelligence intelligence;
    Double requiredValue;
}
