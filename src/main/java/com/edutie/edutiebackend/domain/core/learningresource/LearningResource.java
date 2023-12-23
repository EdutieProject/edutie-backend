package com.edutie.edutiebackend.domain.core.learningresource;

import java.util.Set;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public final class LearningResource extends EntityBase<LearningResourceId> {
    private String overviewText;
    private String exerciseText;
    // many-to-many relationship
    private Set<OptimizationStrategyId> optimizationStrategies;
    // many-to-one relationship
    private LessonSegmentId lessonSegmentId;

    /**
     * Adds optimization strategy
     * @param optimizationStrategyId optimization strategy identifier
     */
    public void addOptimizationStrategy(OptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.add(optimizationStrategyId);
    }

    /**
     * Removes optimization strategy
     * @param optimizationStrategyId optimization strategy identifier
     */
    public void removeOptimizationStrategy(OptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.remove(optimizationStrategyId);
    }
}
