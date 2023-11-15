package com.edutie.edutiebackend.domain.optimizationstrategies;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.optimizationstrategies.identities.OptimizationStrategyId;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: to force it into one table with skillOS or not to - (separate identities)?
public class AbilityOptimizationStrategy extends EntityBase<OptimizationStrategyId> {
    GenerationPrompt prompt;
    Ability ability;
    Double requiredValue;
}
