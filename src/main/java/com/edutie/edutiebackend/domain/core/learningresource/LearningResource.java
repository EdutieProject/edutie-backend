package com.edutie.edutiebackend.domain.core.learningresource;

import java.util.Set;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.learningresource.valueobjects.Exercise;
import com.edutie.edutiebackend.domain.core.learningresource.valueobjects.ResourceOverview;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It is generated for each student exclusively.
 * It may be fixed into the lesson segment or dynamically generated, depending
 * on lessonSegment's isDynamic field.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResource extends EntityBase<LearningResourceId> {
    //TODO: Is strong typing required?
    private ResourceOverview overview;
    private Exercise exercise;
    // many-to-many relationship
    private Set<OptimizationStrategyId> optimizationStrategies;
    private LessonSegmentId lessonSegmentId;

    /**
     * @param optimizationStrategyId
     */
    public void addOptimizationStrategy(OptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.add(optimizationStrategyId);
    }

    /**
     * @param optimizationStrategyId
     */
    public void removeOptimizationStrategy(OptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.remove(optimizationStrategyId);
    }
}
