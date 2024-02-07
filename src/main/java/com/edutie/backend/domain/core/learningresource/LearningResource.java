package com.edutie.backend.domain.core.learningresource;

import com.edutie.backend.domain.core.common.Utilities;
import com.edutie.backend.domain.core.common.base.AuditableEntityBase;
import com.edutie.backend.domain.core.lessonsegment.LessonSegment;
import com.edutie.backend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.core.optimizationstrategies.AbilityOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.backend.domain.core.optimizationstrategies.base.OptimizationStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: (DOMAIN) add hints
public class LearningResource extends AuditableEntityBase<LearningResourceId> {
    private String overviewText;
    private String exerciseText;
    @ManyToMany
    private final Set<IntelligenceOptimizationStrategy> intelligenceOptimizationStrategies = new HashSet<>();
    @ManyToMany
    private final Set<AbilityOptimizationStrategy> abilityOptimizationStrategies = new HashSet<>();
    @ManyToOne(targetEntity = LessonSegment.class, fetch = FetchType.EAGER)
    private LessonSegment lessonSegment;

    /**
     * Recommended constructor which associates the resource with concrete
     * lesson segment.
     */
    public LearningResource(LessonSegment lessonSegment) {
        this.lessonSegment = lessonSegment;
    }

    public Set<OptimizationStrategy<?>> getAllOptimizationStrategies() {
        Set<OptimizationStrategy<?>> optimizationStrategies = new HashSet<>();
        optimizationStrategies.addAll(abilityOptimizationStrategies);
        optimizationStrategies.addAll(intelligenceOptimizationStrategies);
        return optimizationStrategies;
    }

    public <T extends OptimizationStrategy<?>> void addOptimizationStrategy(T optimizationStrategy, Class<T> strategyClass) {
        var optimizationStrategySet = Utilities.findSetOf(strategyClass, this).orElseThrow();
        optimizationStrategySet.add(optimizationStrategy);
    }

    public <T extends OptimizationStrategy<?>> void removeOptimizationStrategy(T optimizationStrategy, Class<T> strategyClass) {
        var optimizationStrategySet = Utilities.findSetOf(strategyClass, this).orElseThrow();
        optimizationStrategySet.remove(optimizationStrategy);
    }

    public <T extends OptimizationStrategy<?>, U> void removeOptimizationStrategyById(U optimizationStrategyId, Class<T> strategyClass) {
        var optimizationStrategySet = Utilities.findSetOf(strategyClass, this).orElseThrow();
        optimizationStrategySet.stream().filter(o -> o.getId() == optimizationStrategyId).findFirst()
                .ifPresent(optimizationStrategySet::remove);
    }

}
