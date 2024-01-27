package com.edutie.edutiebackend.domain.core.optimizationstrategies.base;

import com.edutie.edutiebackend.domain.core.shared.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.shared.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import jakarta.persistence.Embedded;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

/**
 * Class responsible for optimizing learning resources based
 * on required intelligence parameter
 */
@Getter
@Setter
@MappedSuperclass
public class OptimizationStrategy<TTrait extends Enum<TTrait>> extends AuditableEntityBase<OptimizationStrategyId> {
    @Embedded
    private PromptFragment optimizationDescription;
    private TTrait trait;
    private double requiredValue;
}
