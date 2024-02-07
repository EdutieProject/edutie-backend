package com.edutie.backend.domain.core.optimizationstrategies.base;

import com.edutie.backend.domain.core.common.base.AuditableEntityBase;
import com.edutie.backend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
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
public abstract class OptimizationStrategy<TTrait extends Enum<TTrait>> extends AuditableEntityBase<OptimizationStrategyId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name= "optimization_description"))
    private PromptFragment optimizationDescription = new PromptFragment();
    private TTrait trait;
    private Double requiredValue = 0.0;
}
