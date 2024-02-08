package com.edutie.backend.domain.core.optimizationstrategies;

import com.edutie.backend.domain.core.common.studenttraits.Intelligence;
import com.edutie.backend.domain.core.optimizationstrategies.base.OptimizationStrategy;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class IntelligenceOptimizationStrategy extends OptimizationStrategy<Intelligence> {
    @Convert(converter = Intelligence.Converter.class)
    Intelligence intelligence;
    @Override
    public Intelligence getTrait() {
        return intelligence;
    }

    @Override
    public void setTrait(Intelligence intelligence) {
        this.intelligence = intelligence;
    }
}
