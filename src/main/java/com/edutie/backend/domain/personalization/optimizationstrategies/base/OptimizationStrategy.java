package com.edutie.backend.domain.personalization.optimizationstrategies.base;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.common.studenttraits.Ability;
import com.edutie.backend.domain.common.studenttraits.Intelligence;
import com.edutie.backend.domain.personalization.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.personalization.optimizationstrategies.identities.OptimizationStrategyId;
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
    private Double requiredValue = 0.0;

    public abstract TTrait getTrait();
    public abstract void setTrait(TTrait trait);

    private static AbilityOptimizationStrategy create(UserId userId, Ability ability) {
        AbilityOptimizationStrategy abilityOptimizationStrategy = new AbilityOptimizationStrategy();
        abilityOptimizationStrategy.setId(new OptimizationStrategyId());
        abilityOptimizationStrategy.setCreatedBy(userId);
        abilityOptimizationStrategy.setTrait(ability);
        return abilityOptimizationStrategy;
    }

    private static IntelligenceOptimizationStrategy create(UserId userId, Intelligence intelligence) {
        IntelligenceOptimizationStrategy abilityOptimizationStrategy = new IntelligenceOptimizationStrategy();
        abilityOptimizationStrategy.setId(new OptimizationStrategyId());
        abilityOptimizationStrategy.setCreatedBy(userId);
        abilityOptimizationStrategy.setTrait(intelligence);
        return abilityOptimizationStrategy;
    }
}
