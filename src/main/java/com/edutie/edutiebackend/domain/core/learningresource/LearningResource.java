package com.edutie.edutiebackend.domain.core.learningresource;

import java.util.HashSet;
import java.util.Set;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public final class LearningResource extends EntityBase<LearningResourceId> {
    private String overviewText;
    private String exerciseText;
    // many-to-many relationship
    @Transient
    private final Set<OptimizationStrategyId> optimizationStrategies = new HashSet<>();
    // many-to-one relationship
    @Transient
    private LessonSegmentId lessonSegmentId;

    /**
     * Recommended constructor which associates the resource with concrete
     * lesson segment.
     * @param lessonSegmentId lesson segment id
     */
    public LearningResource(LessonSegmentId lessonSegmentId)
    {
        this.lessonSegmentId = lessonSegmentId;
    }

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
