package com.edutie.edutiebackend.domain.core.optimizationstrategies;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.generationprompt.GenerationPrompt;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;

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
public class OptimizationStrategy<TTrait extends Enum<TTrait>> extends AuditableEntityBase<OptimizationStrategyId> {
    GenerationPrompt prompt;
    TTrait trait;
    Double requiredValue;
}
