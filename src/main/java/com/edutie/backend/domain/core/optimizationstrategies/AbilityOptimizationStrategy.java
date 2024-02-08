package com.edutie.backend.domain.core.optimizationstrategies;

import com.edutie.backend.domain.core.common.studenttraits.Ability;
import com.edutie.backend.domain.core.optimizationstrategies.base.OptimizationStrategy;
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
