package com.edutie.edutiebackend.domain.core.learningresource;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresource.valueobjects.Exercise;
import com.edutie.edutiebackend.domain.core.learningresource.valueobjects.ResourceOverview;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.AbilityOptimizationStrategyId;
import com.edutie.edutiebackend.domain.core.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

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
    private Set<AbilityOptimizationStrategyId> optimizationStrategies;
    private LessonSegmentId lessonSegmentId;
    private ResourceOverview overview;
    private Exercise exercise;

    public void addOptimizationStrategy(AbilityOptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.add(optimizationStrategyId);
    }

    public void removeOptimizationStrategy(AbilityOptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.remove(optimizationStrategyId);
    }
}
