package com.edutie.edutiebackend.domain.core.optimizationstrategies;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.AbilityOptimizationStrategyId;
import com.edutie.edutiebackend.domain.core.common.generationprompt.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class responsible for optimizing learning resources
 * using ability parameter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class AbilityOptimizationStrategy extends AuditableEntityBase<AbilityOptimizationStrategyId> {
    GenerationPrompt prompt;
    Ability ability;
    Double requiredValue;
}
