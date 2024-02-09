package com.edutie.backend.domain.personalization.optimizationstrategies;

import com.edutie.backend.domain.common.studenttraits.Ability;
import com.edutie.backend.domain.personalization.optimizationstrategies.base.OptimizationStrategy;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class AbilityOptimizationStrategy extends OptimizationStrategy<Ability> {
    @Convert(converter = Ability.Converter.class)
    Ability ability;

    @Override
    public Ability getTrait() {
        return ability;
    }

    @Override
    public void setTrait(Ability ability) {
        this.ability = ability;
    }
}
