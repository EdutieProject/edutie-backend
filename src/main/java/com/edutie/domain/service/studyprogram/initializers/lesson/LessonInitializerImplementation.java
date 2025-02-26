package com.edutie.domain.service.studyprogram.initializers.lesson;

import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
@Slf4j
public class LessonInitializerImplementation implements LessonInitializer {
    private final SegmentPersistence segmentPersistence;

    @Override
    public Result initializeLesson(Lesson lesson) {
        log.info("Initializing lesson with an initial root segment...");
        Segment initialSegment = Segment.create(lesson.getAuthorEducator(), lesson);
        initialSegment.setName("Initial segment");
        initialSegment.setSnippetDescription("Modify this segment as you like.");
        segmentPersistence.save(initialSegment).throwIfFailure();
        log.info("Lesson initialization success.");
        return Result.success();
    }
}
