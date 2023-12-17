package com.edutie.edutiebackend.domain;

import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.enums.Priority;
import com.edutie.edutiebackend.domain.core.lessonsegment.enums.SourceOrigin;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects.ExternalSource;
import com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects.LearningRequirement;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.HashSet;

@SpringBootTest
public class LessonSegmentTests {

    @Test
    public void lessonSegmentExternalSourceTest()
    {
        LessonSegment lessonSegment = new LessonSegment();
        lessonSegment.setSegmentDescription(
                PromptFragment.of("Hello")
        );
        try {
            var externalSource = new URL("https://youtube.com");
            lessonSegment.addExternalSource(
                    new ExternalSource(externalSource, SourceOrigin.YOUTUBE)
            );
            ExternalSource addedSource = lessonSegment.getExternalSources().stream().toList().get(0);
            assertEquals(
                    addedSource.getOrigin(),
                    SourceOrigin.YOUTUBE
            );
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void learningRequirementsTest()
    {
        LessonSegment lessonSegment = new LessonSegment();
        var learningReq = new LearningRequirement();
        learningReq.setPriority(Priority.MEDIUM);
        learningReq.setDescription(PromptFragment.of("learningReq"));

        lessonSegment.addLearningRequirement(learningReq);

        assertEquals(
                lessonSegment.getLearningRequirements().stream().toList().get(0).getPriority(),
                Priority.MEDIUM
        );
        assertEquals(
                lessonSegment.getLearningRequirements().stream().toList().get(0).getDescription(),
                PromptFragment.of("learningReq")
        );
    }

    @Test
    public void lessonNavigationTest()
    {
        LessonSegment lessonSegment = new LessonSegment();
        var id = new LessonSegmentId();
        lessonSegment.getNavigation().setPreviousElement(id);

        assertEquals(
                lessonSegment.getNavigation().getPreviousElement(),
                id
        );
    }
}
