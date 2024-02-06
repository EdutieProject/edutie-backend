package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.IntelligenceOptimizationStrategy;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearningResourceTests {
    @Test
    public void recommendedInitializationTest()
    {
        var ls = new LessonSegment();
        ls.setId(new LessonSegmentId());
        LearningResource learningResource = new LearningResource(ls);

        assertEquals(learningResource.getLessonSegment(), ls);
    }

    @Test
    public void fullCreationTest()
    {
        LearningResource learningResource = new LearningResource();
        learningResource.setExerciseText("The contents of exercise section");
        learningResource.setOverviewText("The contents of overview section");
        learningResource.addOptimizationStrategy(new IntelligenceOptimizationStrategy(), IntelligenceOptimizationStrategy.class);

        assertNotNull(learningResource.getExerciseText());
        assertNotNull(learningResource.getOverviewText());
        assertFalse(learningResource.getIntelligenceOptimizationStrategies().isEmpty());
    }
}
