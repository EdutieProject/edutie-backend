package com.edutie.backend.domain.core.optimizationstrategies;

import com.edutie.backend.domain.core.common.studenttraits.Ability;
import com.edutie.backend.domain.core.optimizationstrategies.base.OptimizationStrategy;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class AbilityOptimizationStrategy extends OptimizationStrategy<Ability> {
}
