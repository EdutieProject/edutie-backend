package com.edutie.edutiebackend.domain.optimizationstrategies;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.optimizationstrategies.identities.AbilityOptimizationStrategyId;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class AbilityOptimizationStrategy extends EntityBase<AbilityOptimizationStrategyId> {
    GenerationPrompt prompt;
    Ability ability;
    Double requiredValue;
}
