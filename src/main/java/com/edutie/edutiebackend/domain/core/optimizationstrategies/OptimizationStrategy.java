package com.edutie.edutiebackend.domain.core.optimizationstrategies;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * Class responsible for optimizing learning resources based
 * on required intelligence parameter
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class OptimizationStrategy<TTrait extends Enum<TTrait>> extends AuditableEntityBase<OptimizationStrategyId> {
    @Embedded
    private PromptFragment optimizationDescription;
    private TTrait trait;
    private double requiredValue;
}
