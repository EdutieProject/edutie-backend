package com.edutie.backend.domain.learner;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.domain.personalization.optimizationstrategies.IntelligenceOptimizationStrategy;
import org.junit.jupiter.api.Assertions;
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

        Assertions.assertEquals(learningResource.getLessonSegment(), ls);
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
