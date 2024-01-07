package com.edutie.edutiebackend.domain.core;

import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LessonSegmentTests {

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
