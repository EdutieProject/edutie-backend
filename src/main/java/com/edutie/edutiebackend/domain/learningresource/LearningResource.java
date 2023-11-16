package com.edutie.edutiebackend.domain.learningresource;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.learningresource.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.optimizationstrategies.identities.OptimizationStrategyId;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.Exercise;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.Flowchart;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.ResourceOverview;
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
    private Set<OptimizationStrategyId> optimizationStrategies;
    private LessonSegmentId lessonSegmentId;
    private ResourceOverview overview;
    private Exercise exercise;
    private Flowchart flowchart;

    public void addOptimizationStrategy(OptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.add(optimizationStrategyId);
    }

    public void removeOptimizationStrategy(OptimizationStrategyId optimizationStrategyId)
    {
        optimizationStrategies.remove(optimizationStrategyId);
    }
}
