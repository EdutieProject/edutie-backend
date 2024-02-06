package com.edutie.edutiebackend.domain.core.optimizationstrategies;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.base.OptimizationStrategy;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class IntelligenceOptimizationStrategy extends OptimizationStrategy<Intelligence> {
}
