package com.edutie.backend.domain.core.optimizationstrategies;

import com.edutie.backend.domain.core.common.studenttraits.Intelligence;
import com.edutie.backend.domain.core.optimizationstrategies.base.OptimizationStrategy;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class IntelligenceOptimizationStrategy extends OptimizationStrategy<Intelligence> {
}
