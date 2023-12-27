package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.enums.SourceOrigin;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects.ExternalSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LessonSegmentTests {

    @Test
    public void lessonSegmentExternalSourceTest()
    {
        LessonSegment lessonSegment = new LessonSegment();
        lessonSegment.setOverviewDescription(
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
            throw new AssertionError(e);
        }
    }

    /**
     * Demonstration of navigation encapsulated API
     */
    @Test
    public void lessonPrivateNavigationTest()
    {
        LessonSegment lessonSegment = new LessonSegment();
        var id = new LessonSegmentId();
        lessonSegment.getNavigation().setPreviousElement(id);

        assertEquals(
                lessonSegment.getNavigation().getPreviousElement(),
                id
        );
    }

    /**
     * Demonstration of navigation public API
     */
    @Test
    public void lessonPublicNavigationApiTest()
    {
        LessonSegment lessonSegment = new LessonSegment();
        var id = new LessonSegmentId();
        lessonSegment.navigation.setPreviousElement(id);

        assertEquals(
                lessonSegment.navigation.getPreviousElement(),
                id
        );
    }
}
