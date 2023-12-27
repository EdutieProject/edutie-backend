package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.optimizationstrategies.identities.OptimizationStrategyId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LearningResourceTests {
    @Test
    public void recommendedInitializationTest()
    {
        var id = new LessonSegmentId();
        LearningResource learningResource = new LearningResource(id);

        assertEquals(learningResource.getLessonSegmentId(), id);
    }

    @Test
    public void fullCreationTest()
    {
        var id = new LessonSegmentId();
        LearningResource learningResource = new LearningResource(id);
        learningResource.setExerciseText("The contents of exercise section");
        learningResource.setOverviewText("The contents of overview section");
        learningResource.addOptimizationStrategy(new OptimizationStrategyId());

        assertNotNull(learningResource.getExerciseText());
        assertNotNull(learningResource.getOverviewText());
        assertFalse(learningResource.getOptimizationStrategies().isEmpty());
    }
}
